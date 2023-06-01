package com.example.fx.mechanic;
import com.example.fx.AI.AI;
import lombok.*;



import com.example.fx.method;
import com.example.fx.object.Card;

import static com.example.fx.joueurs.joueurs.joueurs;
import static com.example.fx.joueurs.joueurs.joueursPli;
import static com.example.fx.mechanic.Method.*;
import static com.example.fx.object.Card.Allcarte;
@Getter@Setter
public class turn {
    public static int lastcol(int i){
        int lastcol=5;
        while( rangees[lastcol][i].getNum_card()==0){
            lastcol-=1;
        }
        return lastcol;
    }

    public static boolean verif(int i,int choix){
        for (int k = 0; k < 4; k++) {
            int indexLastcol=lastcol(k);
            if (joueurs.get(i).get(choix).getNum_card() > rangees[indexLastcol][k].getNum_card()) {
                return true;
            }
        }
        return false;
    }
    static void turn(int i,int choix){

        int indexRangee = 0;
        int lastcol;
        int indexLastcol=lastcol(0);

        for (int k = 1; k < 4; k++) {
            lastcol = lastcol(k);
            if ((joueurs.get(i).get(choix).getNum_card() > rangees[indexLastcol][indexRangee].getNum_card())) {
                if ((rangees[lastcol][k].getNum_card() > rangees[indexLastcol][indexRangee].getNum_card()) && (rangees[lastcol][k].getNum_card() < joueurs.get(i).get(choix).getNum_card())) {
                    indexRangee = k;
                    indexLastcol = lastcol;
                }
            }

            else{
                indexRangee = k;
                indexLastcol=lastcol;
            }
        }
        if (indexLastcol==4){
            rammasser(i,indexRangee,joueurs.get(i).get(choix));
        }
        else {
            rangees[indexLastcol+1][indexRangee] = joueurs.get(i).get(choix);
        }
        joueurs.get(i).remove(joueurs.get(i).get(choix));
    }
    public static void rammasser(int i, int j, Card cardPlay){
        int indexLastcol=lastcol(j);
        for (int k=0;k<=indexLastcol;k++) {
            joueursPli.get(i).add(rangees[k][j]);
            rangees[k][j]=Card0;
        }
        rangees[0][j]=cardPlay;

    }
    public static void game(){
        for (int i = 0; i < joueurs.size(); i++) {
            plateau();
            if(joueurs.get(i)!= AI.ordimain){
                int choix;
                int choix2;
                show(i);
                System.out.print("Joueur " + (i+1) + ", choisissez une carte : ");
                choix = method.scInt("->",joueurs.get(i).size())-1;
                if (verif(i, choix) == true){
                    Allcarte.remove(joueurs.get(i).get(choix));
                    turn(i,choix);
                }
                else{
                    System.out.print("Joueur " + (i+1) + ", choisissez une colonne : ");
                    choix2 = method.scInt("->",4)-1;
                    rammasser(i,choix2,joueurs.get(i).get(choix));
                    Allcarte.remove(joueurs.get(i).get(choix));
                    joueurs.get(i).remove(joueurs.get(i).get(choix));


                }
            }
            else {
                AI.arbre(50);
                int random =AI.Savemouv.get(0);
                int randomcol=AI.Savemouv.get(1);
                AI.Savemouv.clear();
                if (verif(i, random) == true) {
                    turn(i, random);
                } else {
                    rammasser(i, randomcol, joueurs.get(i).get(random));
                    joueurs.get(i).remove(joueurs.get(i).get(random));
                }
            }

        }

    }
}
