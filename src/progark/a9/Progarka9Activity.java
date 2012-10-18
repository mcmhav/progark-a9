package progark.a9;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import progark.a9.screens.IntroScreen;
import progark.a9.screens.OptionsScreen;
import sheep.game.Game;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author firith
 * This is the Main Class for the Game
 * it inherits from the Android Activity class
 */
public class Progarka9Activity extends Activity {
	
	private EditText mText;
	private Button bt;
	private Game game;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        game = new Game(this, null);
        Constants.main = this;
        Constants.MainGame = game;
        Constants.et = (EditText) findViewById(R.id.editText1);
        Constants.server = getString(R.string.server_address);
        setContentView(R.layout.ipconf);
        
        mText = (EditText) this.findViewById(R.id.editText1);
        bt = (Button) findViewById(R.id.button1);
        
        
        String ipfile = "ipfile";
		
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedReader br = null;
		
		try {
			fis = openFileInput(ipfile);
			bis = new BufferedInputStream(fis);
			br = new BufferedReader(new InputStreamReader(bis));
			String inn = "";
			while(br.ready()){
				inn += br.readLine();
//				mText.setText(fis.toString());
				
			}
			mText.setText(inn);
			fis.close();
			bis.close();
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String ipfile = "ipfile";
				
//				FileInputStream fis = null;
				
//				try {
//					fis = openFileInput(ipfile);
////					int i = fis.read();
////					while(i != -1){
//					mText.setText(fis.toString());
////					}
////					byte[] temp = fis.read(new );
//					fis.close();
//				} catch (FileNotFoundException e1) {
//					e1.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
		        Constants.server = mText.getText().toString();
				
		        
		        
		        FileOutputStream fos = null;
				try {
					fos = openFileOutput(ipfile, Context.MODE_PRIVATE);
					fos.write(Constants.server.getBytes());
					fos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
				
				IntroScreen introScreen = new IntroScreen();
		        game.pushState(introScreen);
		        setContentView(game);
				
				//finish();
			}
		});
    }
       
    /* (non-Javadoc)
     * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
     * If the Back or Home keys are pressed the application exits,
     * not just sleeps like a regular android application
     */
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	finish();
        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {
        	finish();
         }
        return true;
    }
    
    public void onUserLeaveHint(){
        finish();
    }
}