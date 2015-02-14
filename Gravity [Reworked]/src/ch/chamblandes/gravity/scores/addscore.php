<?php
  	//error_reporting(E_ALL ^ E_NOTICE);
  	ini_set("error_reporting", E_ALL - E_NOTICE);
	  ini_set("display_errors", "1");

 	  foreach($_REQUEST as $cle => $valeur) {
 	    $$cle = $valeur;  // transformer les arguments passes en variables
// 	    echo $cle . "=" . $valeur . "<br>";
 	  }
 	  
  	function mysql_die($error = "inconnue") {
  	  global $query;
	  	echo("erreur mysql : " . mysql_error() . "<BR>");
	  	echo "Erreur :". $error ." ($query)<br>)";
	  	exit;
	  }

 	  // Donn�es sur la base :
 	  $hote  = "localhost"; // localhost ?
 	  $utilisateur = "tm2011java"; 
 	  $motdepasse = "phpjava";
 	  $nombase = "TM2011";
 	  $nomtable = "tblScoreGravity";
 	  
// 	  function compareres($a, $b) {
// 	    if ($a["coupstot"] == $b["coupstot"]) {
// 	      if ($a["tempstot"] == $b["tempstot"]) return 0;
// 	      else return ($a["tempstot"] > $b["tempstot"] ? 1 : -1);
// 	    }
// 	    else return ($a["coupstot"] > $b["coupstot"] ? 1 : -1);
// 	  }
 	  
	  
// 	  function getrang($nj) {
// 	  	global $nomtable, $nbtrous, $trouss, $iscore;
//  			$query = "SELECT nojeu,id,coups,temps,COUNT(*) as nbtr,SUM(coups) as totcps,SUM(temps) as tottps" .
//  			         " FROM $nomtable WHERE (trou=" . $trouss[0];
//  			for ($i=1; $i<$nbtrous; $i++) $query .= " OR trou=" . $trouss[$i];
//  			$query .= ") GROUP BY nojeu having nbtr=$nbtrous  ORDER BY totcps, tottps";
//  			//$query .= ") AND nbtr=$nbtrous GROUP BY nojeu"; // ORDER BY totcps, tottps";
//  			//echo "query=$query<br>\n";
//  			if ($result = mysql_query($query)) {
//  				$i = 1;
//  				$rang = -1;
//  				while (($line = mysql_fetch_array($result))) {
//  					if ($nj == $line["nojeu"]) {
//  					  $rang = $i;
//  					  //echo "nojeu=" . $line["nojeu"];
//  						break;
//  					}
//  					//echo "line=" . $line["nojeu"] . " nbtr=" . $line["nbtr"] . "<br>\n";
//  					$i++;
//  				}
//  				return $rang;
//  			}
//  			else return -1;
//  			//else echo "pas trouve de resultat dans la table pour " . ($nbtrous == 1 ? "ce trou" : "ces trous") . " ($trou)";
// 	  }
 	  
    if ($link = mysql_connect($hote, $utilisateur, $motdepasse)) {			
  		if (mysql_select_db ($nombase)) {		
  			$query = "INSERT INTO $nomtable SET sco_name='$name', sco_score=$score, sco_level=$level";	
 		    $result = mysql_query($query) or mysql_die("impossible d'inserer");
 		}
   		else echo "base inaccessible";

//  	   $tempss = split(",", $temps);
// 	     $trouss = split(",", $trou);
// 	     $coupss = split(",", $coups);
// 	     $nbtrous = count($trouss);
 	     
//  	     $maxjeu = 0;
  	     
	      //if (count($tempss) > 1) {
//	          $query = "SELECT MAX(nojeu) as mx FROM $nomtable WHERE 1";
//	          $result = mysql_query($query) or mysql_die("impossible de trouver le maximum");
// 	          if ($result) {
// 	             //echo "result=$result<br>";
// 	              if ($line = mysql_fetch_array($result)) {
// 	                  //echo "line=$line<br>";
// 	                  $maxjeu = 1 + $line["mx"];
// 	              }
// 	          }
 	      // }
 	       //echo "maxjeu=$maxjeu<br>";
 	       
// 	      for ($i=0; $i<$nbtrous; $i++) {
//						$query = "INSERT INTO $nomtable SET pseudo='$pseudo', temps='" . $tempss[$i] 
// 		                     . "', trou=" . $trouss[$i] . ", coups=" . $coupss[$i] . ", nojeu=" . $maxjeu;
// 		        //echo "query=$query<br>";
//	          $result = mysql_query($query) or mysql_die("impossible d'inserer ($i)");
//            if ($result) {
//               
//            }
//            else echo "rajout $i impossible\n"; 
// 	      }
// 	      if ($result) echo getrang($maxjeu);
//  		}
//  		else echo "base inaccessible";
  	}
  	else echo "link impossible";
?>