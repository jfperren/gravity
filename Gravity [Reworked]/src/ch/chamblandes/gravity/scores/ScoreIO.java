package ch.chamblandes.gravity.scores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;

/**
 * Cette classe permet de communiquer avec un base de donnï¿½es MySQL
 * par l'intermï¿½diaire de quelques programmes php. Plus prï¿½cisï¿½ment,
 * elle permet de lire les rï¿½sultats de parcours de minigolf, d'en ajouter
 * et d'en effacer
 **/
public class ScoreIO {
    /*************************************************************************/
    /*
     * Ne touchez pas au code ci-dessous, ou alors ï¿½ vos risques et
     * pï¿½rilsï¿½!
     */
    /*************************************************************************/
    // remarquez que je n'ai pas pris le temps de simplifier le codeï¿½

    String codeBase = "http://php.educanet2.ch/gycham/lschell/minigolf/";

    public ScoreIO() {
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Rajoute les rï¿½sultats contenus dans <i>coups</i> et <i>temps</i> ï¿½
     * chacun des trous contenus dans <i>trou</i> avec le pseudo <i> pseudo</i>
     * et renvoie le rang occupï¿½ par ce rï¿½sultat pour l'ensemble des trous
     * listï¿½s dans <i>trou</i>
     * <p>
     *
     * @param pseudo
     *            String : surnom du joueur
     * @param temps
     *            String : contient les temps en ms pour chaque trou (ex.:
     *            <b>11000,21443,5432</b>)
     * @param trou
     *            String : contient les trous pour lesquels on veut ajouter les
     *            rï¿½sultats
     *            (ex. <b>1,2,4<b> : trous 1, 2 et 4
     * @param coups
     *            String : contient les coups pour chaque trou (ex.:
     *            <b>3,5,6</b>)
     * @return String : renvoie le rang occupï¿½ par ces rï¿½sultats
     */
    public String addScore(String pseudo, String score, String niveau) {
        // make the connection
        String urlstring = codeBase + "addscore.php";
        String parameters = "name=" + pseudo + "&score=" + score + "&level=" + niveau;
        // "pseudo=" + pseudo + "&temps=" + temps + "&trou=" + trou + "&coups="
        // + coups;

        return (this.sendAddress(urlstring, parameters));
    }

    /**
     * Efface les rï¿½sultats pour un parcours avec les trous contenus dans
     * <i>trou</i>
     * dont le rang est <i>iscore</i>
     * <p>
     *
     * @param iscore
     *            String : le rang du rï¿½sultat ï¿½ effacer
     * @param trou
     *            String : contient les trous considï¿½rï¿½s (ex. <b>1,2,4</b> :
     *            trous 1, 2 et 4)
     * @return Renvoie en ï¿½ventuel message d'erreur du fichier php associï¿½
     *         ï¿½ cette mï¿½thode
     **/
    public String clearScore(String iscore, String trou) {
        // make the connection
        String urlstring = codeBase + "clearscore.php";
        String parameters = "iscore=" + iscore + "&trou=" + trou;

        return (this.sendAddress(urlstring, parameters));
    }

    /**
     * Affiche les <i>nbmax</i> meilleurs scores pour les trous
     * figurant dans la chaï¿½ne de caractï¿½res <i>trou</i>
     * <p>
     * Si <i>html</i> est une chaï¿½ne de caractï¿½res non nulle, les
     * rï¿½sultats sont mis dans un tableau et seront affichï¿½s sous cette
     * forme dans un JLabel par exemple
     * <p>
     * Si <i>detail</i> est une chaï¿½ne non nulle, la mï¿½thode affichera
     * ï¿½galement le nombre de coups pour chaque trou.
     * <p>
     * Format de sortie non html :
     * <p>
     * sans dï¿½tailsï¿½:
     * <p>
     * <i> rang:pseudo:nb tot de coups:temps tot.|rang:pseudo: ï¿½ :temps tot.|
     * </i>
     * <p>
     * avec dï¿½tails :
     * <p>
     * <i> rang:pseudo:coups trou1*coups trou ï¿½ *nb tot de coups: temps tot.|
     * ï¿½ </i>
     * <p>
     *
     * @param trou
     *            chaï¿½ne de caractï¿½res, ex.: <b>1,2,4</b> afficher les
     *            rï¿½sultats pour les trous 1, 2 et 4
     * @param nbmax
     *            le nombre (maximal) de rï¿½sultats (lignes) ï¿½ afficher
     * @param html
     *            chaï¿½ne de caractï¿½res nulle ("") ou non nulle ("1")
     * @param chaï
     *            ¿½ne de caractï¿½res nulle ("") ou non nulle ("1")
     * @return chaï¿½ne de caractï¿½res contenant les rï¿½sultats
     */
    public String getScores(String niveau, String nbmax) {
        // make the connection
        String urlstring = codeBase + "getscores.php";
        String parameters = "nbmax=" + nbmax + "&level=" + niveau;
        // if (html.length() > 0) parameters += "&html=1";
        // if (detail.length() > 0) parameters += "&detail=1";

        return (this.sendAddress(urlstring, parameters));
    }

    /**
     * Contacte une adresse internet (<i>urlstring</i>) et retourne ce qu'elle
     * renvoie
     * <p>
     *
     * @param urlstring
     *            String : adresse url
     * @param parameters
     *            String : paramï¿½tres ï¿½ rajouter ï¿½ l'adresse
     */
    public String sendAddress(String urlstring, String parameters) {
        String results = "";
        // System.out.println("urlstring=" + urlstring + " parameters=" +
        // parameters);
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
            String responseMsg = conn.getResponseMessage(); // OK, Forbidden,
            // etc
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // StringBuffer results = new StringBuffer();
            String oneline;
            // results = br.toString();
            while ((oneline = br.readLine()) != null) {
                // results.append(oneline);
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
     * Permet de changer le chemin d'accï¿½s ï¿½ l'endroit oï¿½ se trouvent les
     * fichiers php
     *
     * @param newCodeBase
     *            String : nouvelle adresse internet terminï¿½e par <b>/</b>
     */
    public void setCodeBase(String newCodeBase) {
        codeBase = newCodeBase;
    }

}
