package ao.co.cligest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/*
 * Ficheiro Criado por N. Diwidi
 * Codificado por ND
 */

public class DataMarcacao {
	
	    private  DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
	    public  Calendar parseCal (String dd_mm_yyyy) throws ParseException {
	            Date dt = df.parse (dd_mm_yyyy);
	            Calendar cal = Calendar.getInstance();
	            cal.setTime (dt);
	            return cal;
	    }
	   
	    String Dhoje(int m,int a)
	    {
	    	Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
			int diaAtual = 0;
			int mesAtual = 0;
			int anoAtual = 0;
			if(mes(m) == getMes() && a == getAno())
	        {
				c.setTime(new Date());
				diaAtual = getDia(); // DIA
				mesAtual = c.get(Calendar.MONTH)+1; 
				anoAtual = c.get(Calendar.YEAR);  // ANO
	        }
			else
				{
				c.set(Calendar.MONTH,mes(m));
				c.set(Calendar.YEAR,a);
				diaAtual = c.getActualMinimum(Calendar.DAY_OF_MONTH); // DIA
				mesAtual = c.get(Calendar.MONTH)+1; 
				anoAtual = c.get(Calendar.YEAR);  // ANO
				}
	        String Dhoje = diaAtual+"/"+mesAtual+"/"+anoAtual;
	        return Dhoje;
	    }
	    String Dfim(int m,int a)
	    {
	    	Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
	    	c.set(Calendar.MONTH,mes(m));
			c.set(Calendar.YEAR,a);
	        int diaFim = c.getActualMaximum(Calendar.DAY_OF_MONTH); // DIA
	        int mesAtual = c.get(Calendar.MONTH)+1; 
	        int anoAtual = c.get(Calendar.YEAR);
	        String Dfim  = diaFim+"/"+mesAtual+"/"+anoAtual;
	        return Dfim;
	    }
	    
	    public  List<Integer> getDia (int d,int m,int a)
	    {
	    	List<Integer> dias = new ArrayList<Integer>();
	    	try{
	    		Calendar c1 = parseCal (Dhoje(m,a));
	            Calendar c2 = parseCal (Dfim(m,a));
	            for (Calendar c = (Calendar) c1.clone(); c.compareTo (c2) <= 0; c.add (Calendar.DATE, +1)) {
	                if (c.get (Calendar.DAY_OF_WEEK) == dia(d))
	                    dias.add(c.get(Calendar.DAY_OF_MONTH));
	            }
	    	}catch(ParseException  er){}
	    	  	
	    	return dias;
	    }
	    String toString(Object a){
	    	String s = "" + a;
	    	return s;
	    }
	    public  List<CarEntidade> getDias (int d,int m,int a)
	    {
	    	List<CarEntidade> dias = new ArrayList<CarEntidade>();
	    	
	    	try{
	    		Calendar c1 = parseCal (Dhoje(m,a));
	            Calendar c2 = parseCal (Dfim(m,a));
	            for (Calendar c = (Calendar) c1.clone(); c.compareTo (c2) <= 0; c.add (Calendar.DATE, +1)) {
	                if (c.get (Calendar.DAY_OF_WEEK) == dia(d))
	                {
	                	CarEntidade ce = new CarEntidade();  
	                	ce.setDia(c.get(Calendar.DAY_OF_MONTH)); //= c.get(Calendar.DAY_OF_MONTH);
	                	dias.add(ce);
	                }
	                    
	            }
	    	}catch(ParseException  er){}
	    	  	
	    	return dias;
	    }
	    public  Integer getDiaSemana () 
	    {
	    	Calendar c = Calendar.getInstance(); 
	        c.setTime(new Date()); 
	    	return (c.get(Calendar.DAY_OF_WEEK));
	    }
	    
	    public  Integer getMes ()
	    {
	    	Calendar c = Calendar.getInstance(); 
	        c.setTime(new Date());
	    	return (c.get(Calendar.MONTH)+1);
	    }
	    public  Integer getAno ()
	    {
	    	Calendar c = Calendar.getInstance(); 
	        c.setTime(new Date());
	    	return (c.get(Calendar.YEAR));
	    }
	    public  Integer getDia ()
	    {
	    	Calendar c = Calendar.getInstance(); 
	        c.setTime(new Date());
	    	return (c.get(Calendar.DAY_OF_MONTH));
	    }
	     int dia (int d)
	    {
	    	if(d == 1)
	    		return Calendar.SUNDAY;
	    	if(d == 2)
	    		return Calendar.MONDAY;
	    	if(d == 3)
	    		return Calendar.TUESDAY;
	    	if(d == 4)
	    		return Calendar.WEDNESDAY;
	    	if(d == 5)
	    		return Calendar.THURSDAY;
	    	if(d == 6)
	    		return Calendar.FRIDAY;
	    	if(d == 7)
	    		return Calendar.SATURDAY;
	    	
	    	return 0;
	    }
	    
	    public  int mes (int m)
	    {
	    	if(m == 1)
	    		return Calendar.JANUARY;
	    	if(m == 2)
	    		return Calendar.FEBRUARY;
	    	if(m == 3)
	    		return Calendar.MARCH;
	    	if(m == 4)
	    		return Calendar.APRIL;
	    	if(m == 5)
	    		return Calendar.MAY;
	    	if(m == 6)
	    		return Calendar.JUNE;
	    	if(m == 7)
	    		return Calendar.JULY;
	    	if(m == 8)
	    		return Calendar.AUGUST;
	    	if(m == 9)
	    		return Calendar.SEPTEMBER;
	    	if(m == 10)
	    		return Calendar.OCTOBER;
	    	if(m == 11)
	    		return Calendar.NOVEMBER;
	    	if(m == 12)
	    		return Calendar.DECEMBER;
	    	return 0;
	    }
}
