package com.example.fx.object;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Card {
    private int num_card;
    private int nbTaureau;
    private String pictureURL;


    public static List<Card> cartes = new ArrayList<>();
    public static List<Card> AllcarteV = new ArrayList<>();
    public static List<Card> Allcarte = new ArrayList<>();

    public Card(int n, int num,String pictureURL) {
        num_card = n;
        nbTaureau = num;
        this.pictureURL = pictureURL;

    }

    public static int taureau(int Num_card, int i, String pictureURL) {
        if (Num_card % 10 == 5) {
            cartes.get(i - 1).setNbTaureau(cartes.get(i - 1).getNbTaureau() + 2);
        }
        if (Num_card % 10 == 0) {
            cartes.get(i - 1).setNbTaureau(cartes.get(i - 1).getNbTaureau() + 3);
        }
        if (Num_card % 11 == 0) {
            cartes.get(i - 1).setNbTaureau(cartes.get(i - 1).getNbTaureau() + 5);
        }
        if (Num_card % 10 != 5 && Num_card % 10 != 0 && Num_card % 11 != 0) {
            cartes.get(i - 1).setNbTaureau(cartes.get(i - 1).getNbTaureau() + 1);
        }
        return cartes.get(i - 1).getNbTaureau();
    }

    public static List<Card> cart() {
        // Initialiser les cartes avec les valeurs de 1 à 104
        for (int i = 1; i <= 104; i++) {
            Card card = new Card(0, 0,null);
            cartes.add(card);
            Allcarte.add(card);
            cartes.get(i - 1).setNum_card(i);
            cartes.get(i-1).setPictureURL("file:src/main/resources/cards/" +i+ ".png");
            Allcarte.get(i - 1).setNum_card(i);
            taureau(cartes.get(i - 1).getNum_card(), i,cartes.get(i-1).getPictureURL());
            taureau(Allcarte.get(i - 1).getNum_card(), i,cartes.get(i-1).getPictureURL());
        }
        return cartes;
    }
}