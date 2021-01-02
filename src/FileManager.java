import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

//저장 및 로드 관리
public class FileManager {
	// 싱글톤
	private FileManager() {
	};

	private static FileManager fm = new FileManager();

	public static FileManager getFm() {
		return fm;
	}

//	RPGManager rpg = RPGManager.getRPGManger();
	UserManager um = UserManager.getUserManger();
	GuildManager gm = GuildManager.getGm();
	String fileName = "RPGgame.txt";
	File file = new File(fileName);

	boolean check() {
		boolean rs = false;
		if (file.exists()) {
			rs = true;
		}
		return rs;
	}

	void saveData(RPGManager rpg) {
		String data = "";
		try {
			FileWriter fw = new FileWriter(fileName);
			data += rpg.um.user.money + "\n";// 첫줄 : 소지금
			for (int i = 0; i < 100; i++) {// equipNo
				data += rpg.um.user.equipNo[i] + "/";
			}
			data += "\n";
			data += rpg.um.user.invenCount + "\n";// 인벤 아이템 수
			for (int i = 0; i < rpg.um.user.invenCount; i++) {
				data += rpg.um.user.inven[i][0] + "/" + rpg.um.user.inven[i][1];
				data += "\n";
			}
			// 길드원 정보
			for (int i = 0; i < rpg.gm.guild.size(); i++) {
				data += rpg.gm.guild.get(i).no + "/" + rpg.gm.guild.get(i).name + "/" + rpg.gm.guild.get(i).level + "/";
				data += rpg.gm.guild.get(i).HP + "/" + rpg.gm.guild.get(i).ATK + "/" + rpg.gm.guild.get(i).DEF + "/"
						+ rpg.gm.guild.get(i).inParty + "/";
				data += rpg.gm.guild.get(i).weapon + "/" + rpg.gm.guild.get(i).armor + "/" + rpg.gm.guild.get(i).ring
						+ "\n";
			}
			fw.write(data);
			fw.close();
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void loadData() {
		if (file.exists()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String data = "";
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					data += line + "\n";
				}
				String[] temp = data.split("\n");

				um.user.money = Integer.parseInt(temp[0]);// money
				String[] temp3 = temp[1].split("/");
				for (int i = 0; i < 100; i++) {// equipNo
					um.user.equipNo[i] = Integer.parseInt(temp3[i]);
				}
				um.user.invenCount = Integer.parseInt(temp[2]);// invenCount
				for (int i = 0; i < um.user.invenCount; i++) {// invenIdx
					String[] temp2 = temp[3 + i].split("/");
					um.user.inven[i][0] = Integer.parseInt(temp2[0]);
					um.user.inven[i][1] = Integer.parseInt(temp2[1]);
				}

				gm = GuildManager.getGm();
				gm.guild = new Vector<>();
				if (gm.guild != null) {//nullPointerException 방지
					for (int i = 3 + um.user.invenCount; i < temp.length; i++) {
						String[] temp2 = temp[i].split("/");
						int no = Integer.parseInt(temp2[0]);
						String name = temp2[1];
						int level = Integer.parseInt(temp2[2]);
						int HP = Integer.parseInt(temp2[3]);
						int ATK = Integer.parseInt(temp2[4]);
						int DEF = Integer.parseInt(temp2[5]);
						boolean inparty = temp2[6].equals("true");
						String weapon = temp2[7];
						String armor = temp2[8];
						String ring = temp2[9];

						gm.guild.add(new Guild(no, name, level, HP, ATK, DEF, inparty, weapon, armor, ring));
					}
				}
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
