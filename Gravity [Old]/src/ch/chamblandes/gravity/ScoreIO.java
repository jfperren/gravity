package ch.chamblandes.gravity;

import java.io.*;
import java.net.*;



/**
 * Cette classe permet de communiquer avec un base de donn�es MySQL
 * par l'interm�diaire de quelques programmes php. Plus pr�cis�ment,
 * elle permet de lire les r�sultats de parcours de minigolf, d'en ajouter
 * et d'en effacer
 **/
public class ScoreIO {
    /*************************************************************************/
    /* Ne touchez pas au code ci-dessous, ou alors � vos risques et p�rils�! */
    /*************************************************************************/
    // remarquez que je n'ai pas pris le temps de simplifier le code�
    
    String codeBase = "http://php.educanet2.ch/gycham/lschell/minigolf/";
    
    public ScoreIO() {
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Permet de changer le chemin d'acc�s � l'endroit o� se trouvent les fichiers php
     * @param newCodeBase String : nouvelle adresse internet termin�e par <b>/</b>
     */
    public void setCodeBase(String newCodeBase) {
        codeBase = newCodeBase;
    }
    
    /**
     * Contacte une adresse internet (<i>urlstring</i>) et retourne ce qu'elle renvoie
     * <p>
     * @param urlstring String : adresse url
     * @param parameters String : param�tres � rajouter � l'adresse
     */
    public String sendAddress(String urlstring, String parameters) {
        String results = "";
        //System.out.println("urlstring=" + urlstring + " parameters=" + parameters);
        try {
            URL url = new URL(urlstring);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // post the parameters
            conn.setDoOutput(true);
            OutputStreamWriter wr;
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(parameters);
            wr.flush();
            wr.close();
            
            // now let's get the results
            conn.connect(); // throws IOException
            int responseCode = conn.getResponseCode(); // 200, 404, etc
            String responseMsg = conn.getResponseMessage(); // OK, Forbidden, etc
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
                    );
            //StringBuffer results = new StringBuffer();
            String oneline;
            //results = br.toString();
            while ((oneline = br.readLine()) != null) {
                //results.append(oneline);
                results += oneline + "\n";
            }
            br.close();
        } catch (MalformedURLException mue) {
            System.out.println("malformedURLException");
        } catch (UnknownServiceException use) {
            System.out.println("UnkownServiceException");
        } catch (IOException ioe) {
            System.out.println("IOException");
        }
        
        return results;
        
    }
    
    /**
     * Affiche les <i>nbmax</i> meilleurs scores pour les trous
     * figurant dans la cha�ne de caract�res <i>trou</i><p>
     * Si <i>html</i> est une cha�ne de caract�res non nulle,
     * les r�sultats sont mis dans un tableau et seront
     * affich�s sous cette forme dans un JLabel par exemple<p>
     * Si <i>detail</i> est une cha�ne non nulle, la m�thode
     * affichera �galement le nombre de coups pour chaque trou.
     * <p>
     * Format de sortie non html :<p>
     *   sans d�tails�: <p><i>
     *  rang:pseudo:nb tot de coups:temps tot.|rang:pseudo: � :temps tot.|
     *   </i><p>
     *   avec d�tails : <p><i>
     *  rang:pseudo:coups trou1*coups trou � *nb tot de coups: temps tot.| �
     *   </i><p>
     *
     * @param  trou cha�ne de caract�res, ex.: <b>1,2,4</b> afficher les r�sultats pour les trous 1, 2 et 4
     * @param  nbmax le nombre (maximal) de r�sultats (lignes) � afficher
     * @param  html   cha�ne de caract�res nulle ("") ou non nulle ("1")
     * @param  cha�ne de caract�res nulle ("") ou non nulle ("1")
     * @return cha�ne de caract�res contenant les r�sultats
     */
    public String getScores(String niveau, String nbmax) {
        // make the connection
        String urlstring = codeBase + "getscores.php";  
        String parameters = "nbmax=" + nbmax + "&level=" + niveau;
//        if (html.length() > 0) parameters += "&html=1";
//        if (detail.length() > 0) parameters += "&detail=1";
        
        return(sendAddress(urlstring, parameters));
    }
    
    /**
     * Rajoute les r�sultats contenus dans <i>coups</i> et <i>temps</i> �
     * chacun des trous contenus dans <i>trou</i> avec le pseudo <i> pseudo</i>
     * et renvoie le rang occup� par ce r�sultat pour l'ensemble des trous
     * list�s dans <i>trou</i><p>
     * @param pseudo String : surnom du joueur
     * @param temps  String : contient les temps en ms pour chaque trou (ex.: <b>11000,21443,5432</b>)
     * @param trou   String : contient les trous pour lesquels on veut ajouter les r�sultats
     *               (ex. <b>1,2,4<b> : trous 1, 2 et 4
     * @param coups  String : contient les coups pour chaque trou (ex.: <b>3,5,6</b>)
     * @return String : renvoie le rang occup� par ces r�sultats
     */
    public String addScore(String pseudo, String score, String niveau) {
            // make the connection
            String urlstring = codeBase + "addscore.php";  
            String parameters =
                    "name=" + pseudo + "&score=" + score + "&level=" + niveau ;
 //                   "pseudo=" + pseudo + "&temps=" + temps + "&trou=" + trou + "&coups=" + coups;
            
         return (sendAddress(urlstring, parameters));
    }
    
    /**
     * Efface les r�sultats pour un parcours avec les trous contenus dans <i>trou</i>
     * dont le rang est <i>iscore</i><p>
     * @param iscore String : le rang du r�sultat � effacer
     * @param trou String : contient les trous consid�r�s (ex. <b>1,2,4</b> : trous 1, 2 et 4)
     * @return Renvoie en �ventuel message d'erreur du fichier php associ� � cette m�thode
     **/
    public String clearScore(String iscore, String trou) {
            // make the connection
            String urlstring =
                    codeBase + "clearscore.php";  
            String parameters = "iscore=" + iscore + "&trou=" + trou;
       
        return (sendAddress(urlstring, parameters));
    }
    
    
}

