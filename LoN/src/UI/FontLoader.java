package UI;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class FontLoader {
	
	public static Font loadGrungeFont(){
		try{
			handler = new FileHandler("FontLoaderLogging.txt");
		} catch (IOException e){ 
			handler = new ConsoleHandler();
		}
		log.addHandler(handler);
		
		
		Font ret = null;
		try {
			Path Pat = Paths.get(Board.class.getResource("GrungeHandwriting.ttf").toString());
			String ParsedPat = Pat.toString();
			InputStream myStream = new BufferedInputStream(new FileInputStream(ParsedPat)); 	 
			//InputStream myStream = new BufferedInputStream(new FileInputStream("GrungeHandwriting.ttf"));
			ret = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 30);
			/*InputStream is = this.getClass().getResourceAsStream("GrungeHandwriting.ttf");
			//File fontFile = new File(this.getClass().getResource("../../../Fonts/Handwritting.ttf").toURI());
			this.handwritting = Font.createFont(Font.PLAIN, is);//.deriveFont(Font.PLAIN, 20f);*/
		} catch (FontFormatException e) {
			log.severe(e.getMessage());
		} catch (IOException e) {
			log.severe(e.getMessage());
		} 
		return ret;
	}
	
	private static Handler handler;
	private static Logger log;
}
