package ch10_date_Time_formating;

import java.text.ChoiceFormat;

    class Ch10_17ChoiceFormatEx12 {
        public static void main(String[] args) {
            String pattern = "60#D|70#C|80<B|90#A";             //패턴용 구분자 # , <
            int[] scores = { 91, 90, 80, 88, 70, 52, 60};

            ChoiceFormat form = new ChoiceFormat(pattern);

            for(int i=0;i<scores.length;i++) {
                System.out.println(scores[i]+":"+form.format(scores[i]));
            }
        } // main
    }

