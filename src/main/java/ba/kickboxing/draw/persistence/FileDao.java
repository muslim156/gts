package ba.kickboxing.draw.persistence;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import ba.kickboxing.draw.Player;

public class FileDao implements DAO {

	private String fileName;

	public FileDao(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void savePlayer(Player player) {
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
			out.append(player.toString());
			// out.write(player.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
