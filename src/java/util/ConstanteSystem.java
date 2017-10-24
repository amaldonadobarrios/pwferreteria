/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Admin-amb
 */

public class ConstanteSystem {
    	private static ConstanteSystem instance = null;
	//************************************
	public ConstanteSystem() 
	{		}
			
	public static synchronized ConstanteSystem getInstance() 
	{
		if (instance == null) {		instance = new ConstanteSystem();		}
		return instance;
	}
        
    static String ruc="20551501176";
    static String Razon_social="Novafonte del Peru S.A.C.";
    static double igv=0.18;

    public static String getRuc() {
        return ruc;
    }

    public static String getRazon_social() {
        return Razon_social;
    }

    public static double getIgv() {
        return igv;
    }
    
}
