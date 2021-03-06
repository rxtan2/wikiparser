/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.illinois.cs.cogcomp.wikiparser.jwpl;

import de.tudarmstadt.ukp.wikipedia.datamachine.domain.JWPLDataMachine;
import edu.illinois.cs.cogcomp.wikiparser.wikiparse.WikiExtractParser;
import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
 
/**
 * This class provides the runDM function that is used to call the JWPL DataMachine function.
 */
public class DataMachine {
    public static void main(String [] args){
        String jwplInputDir = args[0];
        String jwplOutput = jwplInputDir + "\\\\output";  // Gets the path of the JWPL output folder
        File f = new File(jwplOutput);
        WikiExtractParser wikiparser = new WikiExtractParser();
        if(!f.exists() && !f.isDirectory()){  // Runs DataMachine if output folder does not exist
           try{
               runDM(jwplInputDir);
           } catch(Exception e){
               wikiparser.logger.severe(e.toString());
           }
        }
    }
    
    public static void runDM(String jwplInputDir) throws Exception {
        System.setProperty("jdk.xml.totalEntitySizeLimit", "500000000");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
        // Path where wiki dump is stored.
        String[] arg = {"english", "Contents", "Disambiguation_pages", jwplInputDir};
        JWPLDataMachine.main(arg);
    }
}
