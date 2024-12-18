import java.util.Scanner;
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
        int n = sc.nextInt();  // 국가의 수
        int k = sc.nextInt();  // 알고 싶은 국가 번호
        List<Country> countries = new ArrayList<>();

        // 국가 데이터 입력
        for (int i = 1; i <= n; i++) {
            int country = sc.nextInt();  // 국가 번호
            countries.add(new Country(country, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        // 메달 수 기준으로 국가들을 정렬
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country c1, Country c2) {
                if (c1.gold != c2.gold) {
                    return Integer.compare(c2.gold, c1.gold);
                }
                if (c1.silver != c2.silver) {
                    return Integer.compare(c2.silver, c1.silver);
                }
                return Integer.compare(c2.iron, c1.iron);
            }
        });

        int rank = 1;
        int prevGold = -1;
        int prevSilver = -1;
        int prevIron = -1;

        // 순위 매기기
        for (int i = 0; i < n; i++) {
            Country country = countries.get(i);

            // 랭킹 계산: 동일한 메달 수치는 랭킹이 동일하게 유지됨
            if (country.gold == prevGold && country.silver == prevSilver && country.iron == prevIron) {
                // 랭킹을 증가시키지 않음
            } else {
                rank = i + 1;  // 새로운 랭킹은 i+1
            }

            // 국가가 k라면, 해당 국가의 순위를 출력하고 종료
            if (country.idx == k) {
                System.out.println(rank);
                break;
            }

            // 이전 메달 정보를 업데이트
            prevGold = country.gold;
            prevSilver = country.silver;
            prevIron = country.iron;
        }
    }
}