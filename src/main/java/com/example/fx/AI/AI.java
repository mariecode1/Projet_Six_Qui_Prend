package com.example.fx.AI;

import lombok.Getter;
import lombok.Setter;
import com.example.fx.joueurs.joueurs;
import com.example.fx.object.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.fx.mechanic.Method.*;
import static com.example.fx.object.Card.Allcarte;

@Getter @Setter
public class AI {

    static Random random = new Random();
    private static int Save;
    private static int Savecol;
    public static Card[][] rangeesV ;
    public static List<Card> ordimain = new ArrayList<>();
    public static List<Card> ordibin = new ArrayList<>();
    public static List<Card> ordimainV = new ArrayList<>();
    public static List<Card> ordibinV = new ArrayList<>();
    public static List<Card> SaveCard = new ArrayList<>();
    public static List<Integer> Savemouv = new ArrayList<>();
    public static List<Card> binV = new ArrayList<>();
    public static List<List<Card>> joueursPliV = new ArrayList<>();
    private static List<Integer> ScoreV = new ArrayList<>();
    private static int l;


    public static void makeListIdentical(Card[][] list1, Card[][] list2) {
        //This function takes 2 lists as an argument and makes list 1 equal to list 2
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list1[0].length; j++) {
                list1[i][j] = list2[i][j];
            }
        }
    }
    public static void arbre(int profondeur){

        Savemouv.add(0);
        Savemouv.add(0);
        Savemouv.add(50);


        for(int i =0;i<profondeur;i++){
            makeListIdentical(rangeesV,rangees);  //on va faire toutes les options à partir de la liste rangeesV
            ordimainV.addAll(ordimain);
            ordibinV.addAll(ordibin);
            chooseMin(GameLogicAI());

            ordibinV.clear();
            binV.clear();
            Allcarte.addAll(SaveCard);
            SaveCard.clear();
        }
    }
    public static void carteRest(){
        List<Card> main = ordimain;
        Allcarte.removeAll(main);

    }
    public static int GameLogicAI(){
        l=0;
        while(ordimainV.size()>0){

            gameV(l);
            l+=1;
        }
        ScoreV.clear();
        int pointV = 0;
        for (int j = 0; j<joueursPliV.get(joueurs.joueurs.size()-1).size();j++){
            pointV += joueursPliV.get(joueurs.joueurs.size()-1).get(j).getNbTaureau();
        }
        return pointV; //Number of points calculated from the number of bull's head
    }
    private static void chooseMin(int point){
        //will choose the move that earns him the fewest points
        int min = Savemouv.get(2);
        if(point<min){
            Savemouv.clear();
            Savemouv.add(Save);
            Savemouv.add(Savecol);
            Savemouv.add(point);

        }
    }


    public static int lastcolV(int i){
        //renvoie le numero de la dernière colonne remplie, sur une certaine ligne du plateau
        int lastcol=5;
        while( rangeesV[lastcol][i].getNum_card()==0){
            lastcol-=1;
        }
        return lastcol;
    }
    public static boolean verifV(Card Cardplay,int choix){
       // Will check that the move is valid, i.e. if the card on the deck is larger than the last card in the deck
        for (int k = 0; k < 4; k++) {
            int indexLastcol=lastcolV(k);
            if (Cardplay.getNum_card() > rangeesV[indexLastcol][k].getNum_card()) {
                return true;
            }
        }
        return false;
    }
    static void turnV(int i,Card cardPlay){
        //play the card choosen
        int indexRangee = 0;
        int lastcol;
        int indexLastcol=lastcolV(0);

        for (int k = 1; k < 4; k++) {  //pour chaque rangée
            lastcol = lastcolV(k);  //prend le num de la derniere colonne contenant une carte
            if ((cardPlay.getNum_card() > rangeesV[indexLastcol][indexRangee].getNum_card())) {
                if ((rangeesV[lastcol][k].getNum_card() > rangeesV[indexLastcol][indexRangee].getNum_card()) && (rangeesV[lastcol][k].getNum_card() < cardPlay.getNum_card())) {
                    indexRangee = k;
                    indexLastcol = lastcol;
                }
            }

            else{
                indexRangee = k;
                indexLastcol=lastcol;
            }
        }
        if (indexLastcol==4){  //s'il y a déjà 5cartes dans la rangée, à la 6e, on ramasse toute la rangée
            rammasserV(i,indexRangee,cardPlay);
        }
        else {  //sinon on ajoute la carte en bout de rangée
            rangeesV[indexLastcol + 1][indexRangee] = cardPlay;
        }
    }
    public static void rammasserV(int i, int j, Card cardPlay){
        int indexLastcol=lastcolV(j);
        for (int k=0;k<=indexLastcol;k++) {
            joueursPliV.get(i).add(rangeesV[k][j]);
            rangeesV[k][j]=Card0;  //la rangée est progressivement vidée
        }
        rangeesV[0][j]=cardPlay;

    }
    public static void gameV(int l){

        int randomInput= (int) (Allcarte.size()*0.75);
        int randomInputCol= random.nextInt(4); //choisit au hasard une ligne parmis les cartes du plateau
        int randomInputAI;
        if (ordimainV.size()!=1) { //tant qu'il y a plus d'1 carte, un choix doit etre fait
            randomInputAI= random.nextInt(ordimainV.size()-1);  //choisit un entier au hasard s'il y a + d'une carte dans le paquet
        }
        else {
            randomInputAI=0;
        }
        if(l==0){
            Save = randomInputAI;
            Savecol = randomInputCol;
        }
        if (verifV(ordimainV.get(randomInputAI), randomInputAI) == true){ //if the move is valid, play the card
            turnV(joueurs.joueurs.size()-1,joueurs.joueurs.get(joueurs.joueurs.size()-1).get(randomInputAI));
            ordimainV.remove(randomInputAI);
        }
        else{
            rammasserV(joueurs.joueurs.size()-1,randomInputCol,ordimainV.get(randomInputAI)); //
            ordimainV.remove(randomInputAI);

        }


        for (int i = 0; i < joueurs.joueurs.size()-1; i++) {
            System.out.println(Allcarte.size());
            if (verifV(Allcarte.get(randomInput), randomInput) == true) {
                turnV(i, Allcarte.get(randomInput));
                SaveCard.add(Allcarte.get(randomInput));
                Allcarte.remove(randomInput);

            } else {
                rammasserV(i, randomInputCol, Allcarte.get(randomInput));
                SaveCard.add(Allcarte.get(randomInput));
                Allcarte.remove(randomInput);
            }
        }


    }
}
