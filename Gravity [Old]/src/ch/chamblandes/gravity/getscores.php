<?php
 	  foreach($_REQUEST as $cle => $valeur) $$cle = $valeur;  // transformer les arguments passes en variables
 	  
 	  // Donn�es sur la base :
 	  $hote  = "localhost"; // localhost ?
 	  $utilisateur = "tm2011java"; 
 	  $motdepasse = "phpjava";
 	  $nombase = "TM2011";
 	  $nomtable = "tblScoreGravity";
 	  
// 	  $timefact = (isset($timefact) ? $timefact : 0.001);
// 	  $nbmax = (isset($nbmax) ? $nbmax : 100000);  // si on ne mentionne pas le nombre d'enregistrements 
// 	                                              // a renvoyer, il les renvoie tous (enfin au plus 100'000)
// 	  //$trou = (isset($trou) ? $trou : 1);
// 	  $trouss = split(",", $trou);
// 	  $nbtrous = count($trouss);
// 	  
// 	  function compareres($a, $b) {
// 	    if ($a["coupstot"] == $b["coupstot"]) {
// 	      if ($a["tempstot"] == $b["tempstot"]) return 0;
// 	      else return ($a["tempstot"] > $b["tempstot"] ? 1 : -1);
// 	    }
// 	    else return ($a["coupstot"] > $b["coupstot"] ? 1 : -1);
// 	  }
 	  
 	  /*
 	  function printarray($a, $str, $k) {
 	  	if (!is_array($a)) echo $str . $k . "=" . $a . "<br>";
 	  	else {
 	  		for ($j=0; $j<count($a); $j++) printarray($a[$j], $str . "&nbsp;", $j);
 	  	}
 	  }
 	  
 	  function printarrayk($a, $str, $k) {
 	  	if (!is_array($a)) echo $str . $k . "=" . $a . "<br>";
 	  	else {
 	  		while (list($key, $val) = each($a)) printarray($val, $str . "_", $key);
 	  	}
 	  }
 	  */
 	  $str = "";
 	  
      if ($link = mysql_connect($hote, $utilisateur, $motdepasse)) { 
  		if (mysql_select_db ($nombase)) { 
  			$query = "SELECT * FROM $nomtable WHERE sco_level = $level  ORDER BY sco_score DESC Limit $nbmax";
  			$result = mysql_query($query) or mysql_die("impossible de lire");
  			while (($line = mysql_fetch_array($result))) { 
  				$str .= $line["sco_name"] . "|" . $line["sco_level"] . "|" . $line["sco_score"] . "\n";
  			}
  			echo $str;
  		}
  		else echo "base inaccessible";
      }
      else echo "link impossible";
//  		
//  		  if (count($trouss) > 1) {
//  		    $query = "SELECT * FROM $nomtable WHERE (trou=" . $trouss[0];
//  		    for ($i=1; $i<count($trouss); $i++) {
//  		      $query .= " OR trou=" . $trouss[$i] ;
//  		    }
//  		    $query .= ")";
//  		    
//	      
//  			if (isset($html)) {
//  			  echo "<html>\n<font color=#FF0000>Classement pour le" . ($nbtrous > 1 ? "s trous " : " trou ") . "$trou</font>\n<table>\n<tr><td><i>Rang</i></td><td><i>Pseudo</i></td><td" .
//  			       (isset($detail) ? " align=center colspan=" . ($nbtrous+1) : "") . "><i>Coups</i></td><td><i>Temps</i></td></td>\n";
//  		      if (isset($detail)) {
//  		        echo "<tr><td colspan=2></td>";
//  		        for ($i=0; $i<$nbtrous; $i++) echo "<td>trou " . $trouss[$i] . "&nbsp;</td>";
//  		        echo "<td><b>tot</b></td><td></td></tr>";
//  		      }
//  		    }
//  			if ($result = mysql_query($query)) {
//   	          while (($line = mysql_fetch_array($result))) { 
//   	            $res[] = $line;
//   	            //print_r($res[count($res)-1]);
//   	            //echo "-";
//   	            //printarrayk($res[count($res)-1],"","");
//   	          }    // s'arreter apres le nbmax-ieme resultat
//   	          
//   	          //printarrayk($res, "", "");
//   	          //print_r($res);
//   	          
//   	          while (list ($cle, $val) = each($res)) {
//   	            //echo "count(val)=" . count($val) . "<br>";
//   	            $parcours["a" . $val["nojeu"]]["a" . $val["trou"]]["temps"] = $val["temps"]; 
//   	            $parcours["a" . $val["nojeu"]]["a" . $val["trou"]]["coups"] = $val["coups"]; 
//   	            $parcours["a" . $val["nojeu"]]["a" . $val["trou"]]["pseudo"] = $val["pseudo"]; 
//              }
//              //printarrayk($parcours, "", "");
//              //print_r($parcours);
//              
//              sort($parcours);
//              reset($parcours);
//              while(list($clenojeu, $valnojeu) = each($parcours)) { 
//                ksort($valnojeu);
//	              reset($valnojeu);
//	              $nbtrous = 0;
//                     $tempstot = 0;
//                    $coupstot = 0;
//                //$tablcoups = array();
//                $coupsstr = "";
//                while (list($cletrou, $valtrou) = each($valnojeu)) {
//                    //while (list($cleres, $valres) = each($valtrou)) {
//                      $tempstot += $valtrou["temps"];
//                      $coupstot += $valtrou["coups"];
//                      $ps = $valtrou["pseudo"];
//                     //$tablecoups[$valtrou["coups"]];
//                     $nbtrous++;
//                     if (isset($detail)) {
//                       if (!isset($html)) $coupsstr .=  $valtrou["coups"] . "*";
//                       else $coupsstr .= "<td align=center>" . $valtrou["coups"] . "</td>";
//                     }
//                   // }
//                }
//                //if (!isset($html)) $coupsstr .= "$coupstot";
//                //else $coupsstr .= "<td align=center><b>" . $coupstot . "</b></td>";
//                //echo "nbtrous=$nbtrous<br>\n";
//                //echo "nojeu=$clenojeu, tempstot=$tempstot, coupstot=$coupstot, =pseudo=" . $ps . "<br>\n";
//                if ($nbtrous == count($trouss))
//                  $resgroupes[] = array("nojeu" => $clenojeu, "tempstot" => $tempstot * $timefact, "coupstot" => $coupstot,
//                                          "coupsstr" => $coupsstr, "pseudo" => $ps);
//              }
//              usort($resgroupes, "compareres");
//              //print_r($resgroupes);
//              
//   		      $i = 0;
//  				while ($i < count($resgroupes) && $i < $nbmax) {     // s'arreter apres le nbmax-ieme resultat
//  				          // afficher le resultat sur une ligne
//  				  if (isset($html)) echo "<tr><td align=center>" . ($i+1) . "</td><td>" . $resgroupes[$i]["pseudo"] .
//  				                          "</td>" . $resgroupes[$i]["coupsstr"] . "<td align=center>" . $resgroupes[$i]["coupstot"] . "</td>"
//  				                           . "<td>" . $resgroupes[$i]["tempstot"] . "</td></tr>\n";
//  		          else echo "" . ($i+1) . ":" .  $resgroupes[$i]["pseudo"] . ":" . $resgroupes[$i]["coupsstr"]  . $resgroupes[$i]["coupstot"] . ":" . $resgroupes[$i]["tempstot"] . "|";
//  		          $i++;
//  				}
// 		    } 
//  			else {
//  			   if (isset($html)) echo "<tr><td span=4></td></tr>";
//  			   else echo "pas de résultat pour ce trou";
//  		    }
//  		    if (isset($html)) echo "</table>\n</html>";
//  		  }
//  		  else {
//  		        // requete : demander tous les resultats pour le trou $trou
//  			$query = "SELECT * FROM $nomtable WHERE trou=$trou ORDER BY coups, temps";
//  			if (isset($html)) echo "<html>\n<font color=ff0000>Classement pour le trou $trou</font><br>\n<table>\n<tr><td><i>Rang</i></td><td><i>Pseudo</i></td><td><i>Coups</i></td><td><i>Temps</i></td></td>\n";
//  			if ($result = mysql_query($query)) {
//  				$i = 1;
//  				while (($line = mysql_fetch_array($result)) && $i <= $nbmax) {     // s'arreter apres le nbmax-ieme resultat
//  				          // afficher le resultat sur une ligne
//  				  if (isset($html)) echo "<tr><td align=center>$i</td><td>" . $line["pseudo"] . "</td><td align=center>" . $line["coups"] . "</td><td>" . ($line["temps"]*$timefact) . "</td></tr>\n";
//  		          else echo "$i:" .  $line["pseudo"] . ":" . $line["coups"] . ":" . ($line["temps"]*$timefact) . "|";
//  		          $i++;
//  				}
//  			}
//  			else {
//  			   if (isset($html)) echo "<tr><td span=4></td></tr>";
//  			   else echo "Pas de resultats pour ce trou ($trou)";
//  		    }
//  		    if (isset($html)) echo "</table>\n</html>";
//  		  }
//  		}
//  		else echo "base inaccessible";
//  	}
?>