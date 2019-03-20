import java.util.*;
class Card {
	Scanner in = new Scanner(System.in);
	Random rand = new Random();

	int generateRandNum() {
		int randNum = rand.nextInt(10) + 1;

		return randNum;
	}

	char generateRandomArrayElem() {
		char[] items = new char[]{'J','Q','K'};

		return items[rand.nextInt(items.length)];
	}

	int playerDrewOrNot() {
		int a = 0;

		System.out.println("\nWould you like to HIT or STAY? (1 = HIT, 2 = STAY)");
		a = in.nextInt();

		return a;
	}

	int playerHitOrStay(int z) {
		int num = 0;

		if( z == 1 ) {
			num = generateRandNum();
		} else {
			num = 0;
		}

		return num;
	}

	int genDealerRandStatus(int t) {
		int status;

		if( t >= 1 && t <= 14 ) {
			status = 1;
		} else if( t >= 21 ) {
			status = 2;
		} else {
			status = rand.nextInt(2) + 1;
		}

		return status;
	}

	int dealerHitOrStay(int s) {
		int num;

		if ( s == 1 ) {
			System.out.println("\nDealer chooses to hit.");
			num = generateRandNum();
		} else {
			System.out.println("\nDealer stays.");
			num = 0;
		}

		return num;
	}

	int determineGameStatus(int m, int n) {
		int status;

		if( m > n && ( m >= 15 && m <= 21 ) ) {
			status = 1;
		} else if( m > 21 ) {
			status = 2;
		} else if( n > m && ( n >= 15 && n <= 21 ) ) {
			status = 3;
		} else if ( n > 21 ) {
			status = 4;
		} else if( m == n ) {
			status = 5;
		} else {
			status = 0;
		}

		return status;
	}

	String determineGameMsg(int s) {
		String msg = "";

		switch(s) {
			case 1:
			msg = "PLAYER WIN!";
			break;

			case 2:
			msg = "PLAYER LOSE!";
			break;

			case 3:
			msg = "DEALER'S WIN!";
			break;

			case 4:
			msg = "DEALER'S LOSE!";
			break;

			case 5:
			msg = "DRAW!";
			break;

			default:
			msg = "";
			break;
		}

		return msg;
	}
}

public class SimpleBlackJack {
	public static void main(String[] args) {
		int p_total = 0, c_total = 0;
		Card obj = new Card();

		System.out.println("Simple Blackjack!\n");
		int p1 = obj.generateRandNum();
		int p2 = obj.generateRandNum();

		System.out.println("You drew " + p1 + " and " + p2);
		p_total = p1 + p2;
		System.out.println("Your total is " + p_total);

		int c1 = obj.generateRandNum();
		int c2 = obj.generateRandNum();

		c_total = c1 + c2;

		System.out.println("\nThe dealer has a " + c1 + " showing, and a hidden card.");
		System.out.println("His total is hidden, too");

		/** PLAYER */
		int d = obj.playerDrewOrNot();

		while( d != 2 ) {
			int hs = obj.playerHitOrStay(d);

			System.out.println("You drew a " + hs);
			p_total = p_total + hs;
			System.out.println("Your total is " + p_total);
			d = obj.playerDrewOrNot();
		}
		/** END PLAYER */

		/** DEALER */
		System.out.println("\nOkay, dealer's turn.");
		System.out.println("His hidden card was a " + c2);
		System.out.println("His total was " + c_total);

		int dealerStatus = obj.genDealerRandStatus(c_total);
		int e = obj.dealerHitOrStay(dealerStatus);

		while( dealerStatus != 2 ) {
			System.out.println("He draws a " + e);
			c_total = c_total + e;
			System.out.println("His total is " + c_total);
			dealerStatus = obj.genDealerRandStatus(c_total);
			e = obj.dealerHitOrStay(dealerStatus);
		}

		System.out.println("\nDealer total is " + c_total);
		System.out.println("Your total is " + p_total);

		int status = obj.determineGameStatus(p_total, c_total);
		String msg = obj.determineGameMsg(status);

		System.out.println("\n" + msg);
		/** END DEALER */
	}
}