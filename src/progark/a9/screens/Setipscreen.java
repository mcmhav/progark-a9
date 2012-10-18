package progark.a9.screens;

import progark.a9.Constants;
import progark.a9.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setipscreen extends Activity{
	private EditText mText;
	private Button bt;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.ipconf);
        
        mText = (EditText) this.findViewById(R.id.editText1);
        bt = (Button) findViewById(R.id.button1);
        
        bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Constants.server = mText.getText().toString();
				
				Constants.MainGame.popState();
				Constants.MainGame.pushState(new OptionsScreen());
				finish();
			}
		});
    }
}
