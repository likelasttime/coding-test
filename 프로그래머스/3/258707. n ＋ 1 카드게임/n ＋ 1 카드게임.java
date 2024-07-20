import java.util.Set;
import java.util.HashSet;

class Solution {
    static int cardCnt;     // 내가 가진 카드의 갯수
    static int n;           // 만들어야 하는 합
    
    /*
        coin: 동전 수
        cards: 중복되지 않는 자연수가 담긴 배열
    */
    public static int solution(int coin, int[] cards) {
        int answer = 1;
        n = cards.length + 1;
        cardCnt = cards.length / 3;     // 내가 가진 카드의 갯수
        boolean flag = false;       // 카드 2장을 뽑아 n을 만들었는지 여부
        Set<Integer> myCards = new HashSet<>();     // 처음에 뽑은 n/3장의 카드들
        Set<Integer> newCards = new HashSet<>();    // 코인을 주고 뽑은 카드들
        int cardsIdx = cardCnt;             // 새로 뽑을 카드 인덱스
        
        for (int i = 0; i < cardCnt; i++) {  // 카드 뭉치에서 카드 n/3장을 뽑아 모두 가지기
            myCards.add(cards[i]);
        }

        while (cardsIdx < cards.length) {       // 라운드가 끝날 때까지
            flag = makeN(myCards, myCards);  // 코인을 쓰지 않고 가지고 있는 카드에서 2장을 뽑아서 n 만들기
            // 카드 두 장 뽑기
            newCards.add(cards[cardsIdx]);      
            newCards.add(cards[cardsIdx + 1]);
            
            if (!flag && coin > 0) {  // 코인을 1개 사용
                flag = makeN(myCards, newCards);
                if (flag) {
                    coin--;
                }
            }
            if (!flag && coin > 1) {  // 코인을 2개 사용
                flag = makeN(newCards, newCards);
                if (flag) {
                    coin -= 2;
                }
            }
            if (!flag) {        // n을 만들 수 없어서 종료
                break;
            }
            answer++;  // 라운드 증가
            cardsIdx += 2;
        }
        return answer;      // 게임에서 도달 가능한 최대 라운드 수
    }

    /*
        카드 두 장을 뽑아서 n을 만들 수 있는지 확인하기
        a: myCards 집합 또는 newCards 집합
        b: myCards 집합 또는 newCards 집합
    */
    public static boolean makeN(Set<Integer> a, Set<Integer> b) {
        for(int i : a) {
            if(b.contains(n - i)) {
                a.remove(i);
                b.remove(n - i);
                return true;
            }
        }
        return false;
    }
}
