import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
   
interface VolleyballTeam {
	public String getVolleyballTeamName();
}

interface BasketballTeam {
	public String getBasketballTeamName();
}

class cmp implements Comparator<TeamBase> {
	public int compare(TeamBase a, TeamBase b) {
		if (a.avHeight() < b.avHeight())
			return 1;
		if (a.avHeight() < b.avHeight())
			return -1;
		return 0;
	}
}

abstract class TeamBase implements VolleyballTeam, BasketballTeam {
	protected String name;
	private int playerCount;
	private double[] height;

	public TeamBase() {
		name = "";
		playerCount = 0;
		height = new double[0];
	}

	public TeamBase(String n, int cnt, double[] h) {

		name = n;
		playerCount = cnt;
		height = new double[cnt];
		for (int i = 0; i < cnt; i++)
			height[i] = h[i];
	}

	public double avHeight() {
		int a = height.length;
		double sum = 0;
		for (int i = 0; i < a; i++) {
			sum += height[i];
		}
		return sum / a;
	}

	public double maxHeight() {
		int a = height.length;
		double max = 0;
		for (int i = 0; i < a; i++) {
			if (height[i] > max)
				max = height[i];
		}
		return max;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	@Override
	public String getBasketballTeamName() {
	     return "Voleyball Team: \"" + name + " V.C.\"";
	}

	@Override
	public String getVolleyballTeamName() {
		return "Basketball Team: \"" + name + " V.C.\"";
	}
}
	class Team extends TeamBase {
		private char kind;

		public Team() {
			super();

		}

		public Team(char k, String n, int cnt, double[] h) {
			super(n, cnt, h);
			kind = k;
		}

		public Team getData(String lineRead) {
			String[] a = lineRead.split(",");

			int br = Integer.parseInt(a[2]);
			double[] h = new double[br];
			for (int i = 0; i < br; i++) {
				h[i] = Double.parseDouble(a[i + 3]);
			}
			String kk = a[0];
			char k = kk.charAt(0);
			String n = a[1];
			return new Team(k, n, br, h);
		}

	

		public String toString() {
			return (kind == 'V' ? getVolleyballTeamName() : getBasketballTeamName()) +
					 name + "\" , Players: " + getPlayerCount() + ", average height: "
					+ String.format("%.2f", avHeight()) + ", maximal height: " + String.format("%.2f", maxHeight());
		}

	}



public class Main {

	public static void main(String[] args) throws Exception {
		ArrayList<Team> teams = new ArrayList<Team>();// Динамичен масив от отбори
		File file = new File("data.txt");// Отваряне на файла
		Scanner sc = new Scanner(file);// Скенер за четене от файл
		while (sc.hasNextLine()) {// Докато има редове
			Team t = new Team(); // Създаване "празен" отбор
			String s = sc.nextLine(); // Четем следващ ред
			if (!s.equals("")) { // Ако не е празен ред
				t = t.getData(sc.nextLine());// Пренасочваме t към нов обект от тип "Отбор"
				teams.add(t);// Добавяме към динамичния масив от отбори
			}
		}
		sc.close();
		teams.sort(new cmp());// Подреждаме отборите по указания приоритет
		// Извеждаме подредения масив на стандартния изход
		for (Team t : teams)
			System.out.println(t);

	}

}
