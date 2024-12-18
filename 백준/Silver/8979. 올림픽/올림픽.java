import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class Main {
    static class Country {
        int idx;
        int gold;
        int silver;
        int iron;

        Country(int idx, int gold, int silver, int iron) {
            this.idx = idx;
            this.gold = gold;
            this.silver = silver;
            this.iron = iron;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 1 <= 국가의 수 <= 1,000
        int k = sc.nextInt();       // 1 <= 알고 싶은 국가 <= n
        int answer = 0;
        List<Country> countries = new ArrayList();

        for(int i=1; i<=n; i++) {
            int country = sc.nextInt();     // 국가 번호
            countries.add(new Country(country, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country c1, Country c2) {
                if(c1.gold != c2.gold) {
                    return Integer.compare(c2.gold, c1.gold);
                }
                if(c1.silver != c2.silver) {
                    return Integer.compare(c2.silver, c1.silver);
                }
                return Integer.compare(c2.iron, c1.iron);
            }
        });

        int idx = 0;
        int prevGold = -1;
        int prevSilver = -1;
        int prevIron = -1;
        int cnt = 0;    // 연속 횟수
        while(true) {
            Country country = countries.get(idx);
            if(country.idx == k) {
                break;
            }
            if(country.gold == prevGold && country.silver == prevSilver
                    && country.iron == prevIron) {
                cnt++;
            } else {
                prevGold = country.gold;
                prevSilver = country.silver;
                prevIron = country.iron;
            }
            answer++;
            idx++;
        }

        System.out.println(answer - cnt);
    }
}