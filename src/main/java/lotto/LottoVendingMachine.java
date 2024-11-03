package lotto;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoVendingMachine {

    public List<Lotto> purchaseLottos() {
        System.out.println("구입금액을 입력해 주세요.");
        String userInput = Console.readLine();

        while (true) {
            if (validateMoney(userInput)) break;
        }

        int money = Integer.parseInt(userInput);
        List<Lotto> lottos = createLottos(money);
        printLottos(lottos);

        return lottos;
    }

    //Todo: 리팩토링 필요
    //Todo: 15줄 이하
    public boolean validateMoney(String userInput) {

        try {
            int money = Integer.parseInt(userInput);

            if(money < 1000){
                throw new IllegalArgumentException("로또는 최소 1장 이상 구매해야 합니다.");
            }

            if(money > 50000){
                throw new IllegalArgumentException("로또는 최대 50장까지 구매 가능합니다.");
            }

            if(money % 1000 != 0){
                throw new IllegalArgumentException("로또 구입 금액은 1000원으로 나누어 떨어져야 합니다.");
            }

            return true;

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 유효한 숫자를 입력해야 합니다.");
            return false;

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] "+e.getMessage());
            return false;

        }
    }

    public List<Lotto> createLottos(int money){
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 1; i <= (money / 1000); i++) {
            lottos.add(new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)));
        }

        return lottos;
    }

    private void printLottos(List<Lotto> lottos){

        System.out.println(lottos.size()+"개를 구매했습니다.");

        for(Lotto lotto : lottos){
            lotto.printNumbers();
        }
    }
}
