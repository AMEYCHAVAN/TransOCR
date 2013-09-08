package com.datumdroid.android.ocr.simple;
 
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class transappengine extends Activity  implements OnClickListener{


		EditText e1,e2;
		Button b1;
		String[] items11 = { "ENGLISH", "HINDI", "FRENCH", "GERMAN","ITALIAN", "RUSSIAN","SPANISH"};
		String[] items22 = { "ENGLISH", "HINDI", "FRENCH", "GERMAN","ITALIAN", "RUSSIAN","SPANISH"};
	Language[] langlist={Language.ENGLISH,Language.HINDI,Language.FRENCH,Language.GERMAN,Language.ITALIAN,Language.RUSSIAN,Language.SPANISH};
	Language tox,fromx;
	  String TAG = null;


	  
		protected void onCreate(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		
			Log.v(TAG, "stage 2");
			setContentView(R.layout.transmain);
			e1 = (EditText) findViewById(R.id.editText1);
			 Bundle extras = getIntent().getExtras();
			 String value = extras.getString("op");
			  
			  
			e1.setText(value);
			Log.v(TAG, "stage 3");
			//////////////
	
		

		    Spinner spin = (Spinner) findViewById(R.id.spinner1);
	        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items11);
	        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spin.setAdapter(aa);
	        spin.setOnItemSelectedListener(new tospin());

	        
	        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
	        ArrayAdapter<String> bb = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items22);
	        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spin2.setAdapter(bb);
	        spin2.setOnItemSelectedListener(new fromspin());
			
	        b1=(Button) findViewById(R.id.buttontwo);
			b1.setOnClickListener(this);
			
		}
		 
		
		

		class tospin implements AdapterView.OnItemSelectedListener
	    {
	        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
	        {
	            Toast.makeText(v.getContext(), "Your input Language is :" + items11[position],Toast.LENGTH_SHORT).show();      
	      
	        fromx=langlist[position];
	        
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    }

	    class fromspin implements AdapterView.OnItemSelectedListener
	    {
	        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
	        {
	            Toast.makeText(v.getContext(), "Your Output langugage  is :" + items22[position],Toast.LENGTH_SHORT).show();        
	            tox=langlist[position];
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    }

		
		
		
		
		
	 

	@Override
	public void onClick(View v) {
		
		
		
		//	 String FRENCH="fr";
		//   String ENGLISH="en";
			  Translate.setHttpReferrer("https://api.datamarket.azure.com/Bing/MicrosoftTranslator/");
			  Translate.setClientId("translating-chat");
	          Translate.setClientSecret("YLW/pu1xmFPqwOtpfvBC9Rb3NSk77VBeljuYP7Ztpto=");
		    
			     
		          
			        //String message="Bonjour ";
	          EditText e1 = (EditText)findViewById(R.id.editText1);
	          String message=e1.getText().toString();
			        System.out.println("Input is: " + message + "\n");
			      
			               
	  try {
			            String translatedMessages = Translate.execute(message,fromx, tox);
			            System.out.println("Output is: " + translatedMessages + "\n");
			            EditText e2 = (EditText)findViewById(R.id.editText2);
					       e2.setText( translatedMessages);  
			           
			 
	  		}
			        catch (Exception e) {
			        //    System.out.println("Error while translating message: " + message + "\n");
			            System.out.println("\tError: " + e.getMessage() + "\n");
			            e2.setText(" + e.getMessage() ");
			            //if we can't translate the message, we can at least send off the original
			            
			        }  
	}	
}