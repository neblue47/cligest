package ao.co.cligest.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PessoaTest {

	private int idade;
	private int meses;
	private int anos ;
	private int dias;
	
	
	
	public static void main(String[] agrs) {
		String data = "18/12/2017";
        Date dataNascimento = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            dataNascimento = df.parse(data);
        } catch (ParseException ex) {
        }
        String idadeEmAnosMesesDias = calcularIdade(dataNascimento);
        System.out.println("Idade: " + formatarIdade(idadeEmAnosMesesDias));
	}
	
	public static String calcularIdade(Date data) {
        Integer anos = 0;
        Integer meses = 0;
        Integer dias = 0;
        Calendar dataAtual;
        Calendar dataNascimento;
        dataAtual = Calendar.getInstance();
        dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(data);
        anos = anos + (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));
        meses = meses + (dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH));
        dias = dias + (dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH));
        
        if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
            }
        } else if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) >= dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                meses = 12 + meses;
                anos = anos - 1;
            } else if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
            }
        } else if (dataAtual.get(Calendar.MONTH) > dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = meses - 1;
            }
        }
        return formatar(anos, meses, dias);
    }
	
	/** Método responsável por formatar uma idade no formato 1 10 6.
     *  Concatena-se 0 se o valor menor que 10 e logo apos o numero adicionasse espaço.
     * 
     *  @return Data formatada para o formato 00 00 00.
     */
    public static String formatar(Integer ano, Integer mes, Integer dia) {
        String idade = "";
        if (ano.intValue() < 10) {
            idade = (new StringBuilder()).append(idade).append("0").append(ano).append(" ").toString();
        } else {
            idade = (new StringBuilder()).append(idade).append(ano).append(" ").toString();
        }
        if (mes.intValue() < 10) {
            idade = (new StringBuilder()).append(idade).append("0").append(mes).append(" ").toString();
        } else {
            idade = (new StringBuilder()).append(idade).append(mes).append(" ").toString();
        }
        if (dia.intValue() < 10) {
            idade = (new StringBuilder()).append(idade).append("0").append(dia).append(" ").toString();
        } else {
            idade = (new StringBuilder()).append(idade).append(dia).append(" ").toString();
        }
        return idade;
    }
    
    /** Pega os valores 00 00 00 e coloca Ano(s) Mes(es) Dia(s)
     */
     public static String formatarIdade(String ida) {
         String idade = "";
         String anos = null;
         String meses = null;
         String dias = null;
         String CharArray[] = ida.split(" ");
         anos = CharArray[0];
         meses = CharArray[1];
         dias = CharArray[2];
         if (!anos.equals("00")) {
             if (anos.equals("01")) {
                 idade = idade.concat((new StringBuilder()).append(anos).append(" Ano ").toString());
             } else {
                 idade = idade.concat((new StringBuilder()).append(anos).append(" Anos ").toString());
             }
         }
         if (!meses.equals("00")) {
             if (meses.equals("01")) {
                 idade = idade.concat((new StringBuilder()).append(meses).append(" Mes ").toString());
             } else {
                 idade = idade.concat((new StringBuilder()).append(meses).append(" Meses ").toString());
             }
         }
         if (!dias.equals("00")) {
             if (dias.equals("01")) {
                 idade = idade.concat((new StringBuilder()).append(dias).append(" Dia ").toString());
             } else {
                 idade = idade.concat((new StringBuilder()).append(dias).append(" Dias").toString());
             }
         }
         return idade;
     }

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}
	
	public void calcularIdadeEmDetalhe(Date data)
	{
		Integer anos = 0;
        Integer meses = 0;
        Integer dias = 0;
        Calendar dataAtual;
        Calendar dataNascimento;
        dataAtual = Calendar.getInstance();
        dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(data);
        anos = anos + (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));
        meses = meses + (dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH));
        dias = dias + (dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH));
        
        if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
                this.dias = dias;
                this.meses = meses;
                this.setAnos(anos);
            }
        } else if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) >= dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                meses = 12 + meses;
                anos = anos - 1;
                
                this.meses = meses;
                this.setAnos(anos);
            } else if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
                
                this.dias = dias;
                this.meses = meses;
                this.setAnos(anos);
            }
        } else if (dataAtual.get(Calendar.MONTH) > dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = meses - 1;
                
                this.dias = dias;
                this.meses = meses;
            }
        }
	}

	public int getAnos() {
		return anos;
	}

	public void setAnos(int anos) {
		this.anos = anos;
	}
}
