package by.todes.i_sokolov.parmandroidproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by i_sokolov on 12.08.2014.
 */
public class LoginActivity extends Activity implements OnClickListener {

    private SlidingMenu slidingMenu ;


    EditText editName;
    EditText editPass;
    EditText editAddServer;
    EditText editNameServer;
    EditText editNewPass;
    EditText editConfirmPass;
    ImageButton btnOk;
    ImageButton btnCancel;
    //	Button btnChagePass;
    ImageButton btnAddServ;
    ImageButton btnRemoveServ;
    ImageButton btnCheckServer;
    Intent intent;
    Context conThis;
    AsyncClass ac;
    Singleton ms;
    SoapConstants servinf;
    Boolean reachable = false;
    SharedPreferences sPref;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapterPpr;
    AutoCompleteTextView autoCompleteTextView;

    Spinner spinnerPpr;
    Spinner spinner2;
    ArrayList<String> data;
    List<String> data2;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_layout);

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
//        slidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);

        getActionBar().setDisplayHomeAsUpEnabled(true);


        ms = Singleton.getInstance();
        servinf = SoapConstants.getInstance();
        intent = new Intent(this,LoginActivity.class);
        btnOk = (ImageButton) findViewById(R.id.btnOk);
        btnOk.setEnabled(false);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
//		btnChagePass = (Button) findViewById(R.id.btnChagePass);
        btnAddServ = (ImageButton) findViewById(R.id.btnAddServer);
        btnRemoveServ = (ImageButton) findViewById(R.id.btnDelServer);
        btnCheckServer = (ImageButton)findViewById(R.id.btnCheckServer);
//		btnChagePass.setEnabled(false);
        editName = (EditText) findViewById(R.id.userName);
        editPass = (EditText) findViewById(R.id.userPassword);
        ms.setKPP("");
        editName.setText("PHOENIX");
        editPass.setText("pho321");
        conThis = this;

        sPref = getSharedPreferences("ParmSettings", MODE_PRIVATE);

        Boolean emptyPreference = !sPref.contains("SYS_TRIP_TYPE");
        if (emptyPreference) {
            sPref.edit().putInt("SYS_TRIP_TYPE", 0).commit();
            sPref.edit().putInt("VECTOR", 0).commit();
            sPref.edit().putInt("COUNTRY", 0).commit();
            sPref.edit().putInt("TARGET", 0).commit();
            sPref.edit().putInt("TYPE_DOCUMENT", 0).commit();
            sPref.edit().putInt("TYPE_VISA", 0).commit();
            sPref.edit().putInt("KIND_VISA", 0).commit();
            sPref.edit().putInt("CITIZENSHIP", 0).commit();
            sPref.edit().putInt("MAIN_DOCUMENT", 0).commit();
            sPref.edit().putString("SERVERS", "").commit();
            sPref.edit().putInt("ITEMSERV", 0).commit();
        }
        ms.setListServers(sPref.getString("SERVERS", ""));
        if (sPref.getString("SERVERS", "").equals("")) {
            data = new ArrayList<String>();
        } else {
            data = new ArrayList<String>(Arrays.asList(ms.getListServers()[1]));
        }


        data2 = new ArrayList<String>();
        adapterPpr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data2);
        adapterPpr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPpr = (Spinner) findViewById(R.id.spinnerPpr);
        spinnerPpr.setAdapter(adapterPpr);
        spinnerPpr.setPrompt("ППР");
        spinnerPpr.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                ms.setKPP(ms.getPpr()[0][arg2]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2 = (Spinner) findViewById(R.id.spinnerServers);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(sPref.getInt("ITEMSERV", 0));
        spinner2.setPrompt("Сервер подключения");
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                CheckServerIpAndGetPPR chppr = new CheckServerIpAndGetPPR();
                chppr.execute(arg2);
                adapterPpr.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        OnClickListener oclBtnOk = new OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnOk:

                        if (!ValidateMenu())
                            break;

                        ms.setName(editName.getText().toString());
                        ms.setPass(editPass.getText().toString());
                        ac = new AsyncClass();
                        ac.execute("1", "");

                        break;
                    case R.id.btnCancel:
                        LoginActivity.super.onBackPressed();
                        break;
                    case R.id.btnAddServer:
                        ms.setServerItem(sPref.getString("SERVERS", ""));
                        showDialog(1);
                        break;
                    case R.id.btnDelServer:
                        if (spinner2.getCount() > 0) {
                            showDialog(2);
                        }
                        break;

//				case R.id.btnChagePass:
//					if (!ValidateMenu())
//						break;
//					showDialog(3);
//					break;
                    case R.id.btnCheckServer:
                        CheckServerIpAndGetPPR checkPprExecute = new CheckServerIpAndGetPPR();
                        checkPprExecute.execute(spinner2.getSelectedItemPosition());
                        break;
                    default:
                        break;
                }
            }
        };

        btnOk.setOnClickListener(oclBtnOk);
        btnCancel.setOnClickListener(oclBtnOk);
        btnAddServ.setOnClickListener(oclBtnOk);
//		btnChagePass.setOnClickListener(oclBtnOk);
        btnRemoveServ.setOnClickListener(oclBtnOk);
        btnCheckServer.setOnClickListener(oclBtnOk);
    }

    private Boolean ValidateMenu() {
        if ((editName.getText().toString().equals(""))
                || (editPass.getText().toString().equals(""))) {
            Toast.makeText(conThis, "Не корректно введены данные",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (ms.getKPP().equals("")) {
            Toast.makeText(conThis, "Укажите ППР", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sPref.getString("SERVERS", "").equals("")) {
            Toast.makeText(conThis, "Укажите Сервер", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

    }


    private class CheckServerIpAndGetPPR  extends AsyncTask<Integer , Void , Integer>{
        private ProgressDialog pd;
        private SoapObject resa;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(conThis);
            pd.setMessage("Проверка соединения, ждите...");
            pd.show();

        }

        @Override
        protected Integer doInBackground(Integer... params) {
            servinf.setControlURL(ms.getListServers()[0][spinner2.getSelectedItemPosition()].toString());
            servinf.setCommonURL(ms.getListServers()[0][spinner2.getSelectedItemPosition()].toString());
            SoapObject request = new Requests().getPPR();
            resa = new SoapRequest().sendRequest(request,
                    servinf.getCommonURL());

            return params[0];
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            pd.cancel();

            if (resa != null) {

                sPref.edit().putInt("ITEMSERV", result).commit();
                servinf.setControlURL(ms.getListServers()[0][spinner2
                        .getSelectedItemPosition()]);
                servinf.setCommonURL(ms.getListServers()[0][spinner2
                        .getSelectedItemPosition()]);
                GetPPr gp = new GetPPr();
                gp.execute();
            } else {
                Toast.makeText(
                        conThis,
                        "Сервер: "
                                + spinner2.getItemAtPosition(
                                spinner2.getSelectedItemPosition())
                                .toString() + " недоступен",
                        Toast.LENGTH_LONG).show();
                btnOk.setEnabled(false);
//				btnChagePass.setEnabled(false);
            }
        }



    }

    private class CheckServerIp extends AsyncTask<Void , Void , Void>{

        @Override
        protected Void doInBackground(Void... params) {
//			try {
//				InetAddress	adress = InetAddress
//							.getByName(editAddServer.getText().toString());
//				reachable = adress.isReachable(3000);
//				} catch (UnknownHostException e) {
//					// TODO Auto-generated catch block
//					reachable = false;
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					reachable = false;
//					e.printStackTrace();
//				}
            return null;

        }
        @SuppressWarnings("deprecation")
        @SuppressLint("DefaultLocale")
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if ( 1 == 2 ) {
                Toast.makeText(
                        getApplicationContext(),
                        "Сервер "
                                + editAddServer.getText()
                                .toString()
                                + "  не доступен",
                        Toast.LENGTH_SHORT).show();
            } else {
                sPref.edit().putString("SERVERS", ms.getServerItem()+ editNameServer.getText().toString().toUpperCase() + "#" + editAddServer.getText().toString() + "$").commit();
                ms.setListServers(sPref.getString("SERVERS", ""));
                adapter2.add(editNameServer.getText().toString()
                        .toUpperCase());
                adapter2.notifyDataSetChanged();
                editAddServer.setText("");
                editNameServer.setText("");
                LoginActivity.this.dismissDialog(1);
            }
        }

    }


    //перегрузка onCreateDialog
//1-add
//2-delete
//3-change password
    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);

//add server
        if (id == 1) {
            android.content.DialogInterface.OnClickListener addServerClickListener = new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case Dialog.BUTTON_NEGATIVE:
                            editAddServer.setText("");
                            editNameServer.setText("");
                            break;
                    }
                }
            };
            LinearLayout view = (LinearLayout) getLayoutInflater().inflate(
                    R.layout.dialog_add_server, null);
            editAddServer = (EditText) view.findViewById(R.id.editOwner);
            editNameServer = (EditText) view.findViewById(R.id.editText1);
            dialog.setTitle("Добавить сервер?");
            dialog.setIcon(android.R.drawable.ic_dialog_info);
            dialog.setView(view);
            dialog.setPositiveButton("Да", null);
            dialog.setNegativeButton("Нет", addServerClickListener);
        }

//delete server
        if (id == 2) {
            android.content.DialogInterface.OnClickListener deleteServerClickListener = new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case Dialog.BUTTON_POSITIVE:
                            int selectItem = spinner2.getSelectedItemPosition();
                            adapter2.remove(spinner2.getItemAtPosition(
                                    spinner2.getSelectedItemPosition()).toString());
                            ms.setServerItem(sPref.getString("SERVERS", ""));
                            String item = null;
                            for (int i = 0; i <= spinner2.getSelectedItemPosition(); i++) {
                                item = ms.getServerItem().substring(0,
                                        ms.getServerItem().indexOf("$") + 1);
                                ms.setServerItem(ms.getServerItem().substring(
                                        ms.getServerItem().indexOf("$") + 1));
                            }
                            ms.setServerItem(sPref.getString("SERVERS", "")
                                    .replace(item, ""));
                            sPref.edit().putString("SERVERS", ms.getServerItem())
                                    .commit();
                            adapter2.notifyDataSetChanged();
                            if (selectItem == spinner2.getSelectedItemPosition()) {
                                if (spinner2.getCount() == 0) {
                                    adapterPpr.clear();
                                    adapterPpr.notifyDataSetChanged();
                                    btnOk.setEnabled(false);
//								btnChagePass.setEnabled(false);
                                } else {
                                    CheckServerIpAndGetPPR chppr = new CheckServerIpAndGetPPR();
                                    chppr.execute(selectItem);
                                }

                            }

                            break;
                    }
                }
            };
            dialog.setTitle("Удалить сервер:  "
                    + spinner2.getItemAtPosition(
                    spinner2.getSelectedItemPosition()).toString()
                    + "?");
            dialog.setIcon(android.R.drawable.ic_dialog_info);
            dialog.setPositiveButton("Да", deleteServerClickListener);
            dialog.setNegativeButton("Нет", null);
        }

        if (id == 3) {
            android.content.DialogInterface.OnClickListener myClickListener = new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case Dialog.BUTTON_NEGATIVE:
                            editConfirmPass.setText("");
                            editNewPass.setText("");
                            break;
                    }
                }
            };
            LinearLayout view = (LinearLayout) getLayoutInflater().inflate(
                    R.layout.dialog_add_server, null);
            editConfirmPass = (EditText) view.findViewById(R.id.editOwner);
            editNewPass = (EditText) view.findViewById(R.id.editText1);
            dialog.setTitle("Смена пароля");
            dialog.setIcon(android.R.drawable.ic_dialog_info);
            dialog.setView(view);
            dialog.setPositiveButton("Войти", null);
            dialog.setNegativeButton("Отмена", myClickListener);
        }

        return dialog.create();
    }

    @Override
    protected void onPrepareDialog(int id, final Dialog dialog) {
        if (id == 1) {

            View.OnClickListener onClickListener_btnOK = new View.OnClickListener() {

                @SuppressLint("DefaultLocale")
                public void onClick(View arg0) {
                    if (editNameServer.getText().toString().equals("")
                            || editAddServer.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Заполните все поля", Toast.LENGTH_SHORT)
                                .show();
                    } else {
//                            SharedPreferences.Editor editor = sPref.edit();
//                            editor.putString("SERVERS",editAddServer.getText().toString());
//                            editor.apply();
                        editAddServer.getText().toString();

                        CheckServerIp chs = new CheckServerIp();
                        chs.execute();
                    }
                }
            };

            Button btnOk = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
            btnOk.setOnClickListener(onClickListener_btnOK);
        }

        if (id == 3){
            View.OnClickListener btn_enter = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (editConfirmPass.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                    }else{
                        if (!editConfirmPass.getText().toString().equals(editNewPass.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                        } else{
                            ms.setName(editName.getText().toString());
                            ms.setPass(editPass.getText().toString());
                            ac = new AsyncClass();
                            ac.execute("4", editNewPass.getText().toString());
                            editConfirmPass.setText("");
                            editNewPass.setText("");
                            dialog.dismiss();
                        }
                    }

                }
            };
            Button btnOk = ((AlertDialog) dialog)
                    .getButton(AlertDialog.BUTTON_POSITIVE);
            btnOk.setOnClickListener(btn_enter);
        }

        if (id == 2) {
            dialog.setTitle("Удалить сервер: "
                    + spinner2.getItemAtPosition(
                    spinner2.getSelectedItemPosition()).toString()
                    + "?");
        }
    }

    private class GetPPr extends AsyncTask<Void, Void, SoapObject> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(conThis);
            pd.setMessage("Идёт загрузка, ждите...");
            pd.show();
        }

        @Override
        protected SoapObject doInBackground(Void... params) {
            SoapObject request = new Requests().getPPR();
            SoapObject resa = new SoapRequest().sendRequest(request,
                    servinf.getCommonURL());
            ms.setPpr(resa);
            return resa;
        }

        @Override
        protected void onPostExecute(SoapObject result) {
            super.onPostExecute(result);
            data2 = new ArrayList<String>(Arrays.asList(ms.getPpr()[1]));
            adapterPpr.clear();
            for (int i = 0; i < data2.size(); i++) {
                adapterPpr.add(data2.get(i));
            }
            adapterPpr.notifyDataSetChanged();
            spinnerPpr.setSelection(0);
            pd.cancel();
            btnOk.setEnabled(true);
//			btnChagePass.setEnabled(true);
        }

    }

    private class AsyncClass extends AsyncTask<String, Void, SoapObject> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(conThis);
            pd.setMessage("Загружается, ждите...");
            pd.show();
        }

        @Override
        protected SoapObject doInBackground(String... params) {
            SoapObject request = new Requests().getLoginIn(editName.getText()
                            .toString(), editPass.getText().toString(), ms.getKPP(),
                    params[0], params[1]);
            SoapObject resa = new SoapRequest().sendRequest(request,
                    servinf.getControlURL());
            SoapObject result = new SoapRequest().sendRequest(request,
                    servinf.getControlURL());


            result = (SoapObject) resa.getProperty(1);
            if (!result.getProperty(0).toString().equals(null))
            {
                ms.setIdUser(result.getProperty("paramName").toString());
            }

            resa = (SoapObject) resa.getProperty(0);



            if (resa.getProperty(0).toString().equals("0")) {


                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "-4");
                SoapObject klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setSysTripType(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "1");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setCountry(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "84");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setModelAuto(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "32");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setUnits(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "-8");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setClassAuto(klUni);


                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "4");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setTarget(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "30");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setTypeDocument(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "3");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setTypeVisa(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "35");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setKindVisa(klUni);

                request = new Requests().getKlUni(
                        editName.getText().toString(), editPass.getText()
                                .toString(), ms.getKPP(), "22");
                klUni = new SoapRequest().sendRequest(request,
                        servinf.getCommonURL());
                ms.setMainDocument(klUni);

                request = new Requests().getKlUni(editName.getText().toString(), editPass.getText().toString(), ms.getKPP(), "-15");
                klUni = new SoapRequest().sendRequest(request, servinf.getCommonURL());
                ms.setStatus(klUni);

                ms.setVector();

                request = new Requests().getUserDetails();
                ms.setUser(new SoapRequest().sendRequest(request,
                        servinf.getCommonURL()));

            }
            return resa;
        }

        @Override
        protected void onPostExecute(SoapObject result) {
            super.onPostExecute(result);
            pd.cancel();
            if (result.getProperty(0).toString().equals("-3")
                    || result.getProperty(0).toString().equals("-2")
                    || result.getProperty(0).toString().equals("-1")) {
                Toast.makeText(conThis, result.getProperty(1).toString(),
                        Toast.LENGTH_LONG).show();
            } else {
                startActivity(intent);
            }

        }
    }

    @Override
    public void onBackPressed() {
        if ( slidingMenu.isMenuShowing()) {
            slidingMenu.toggle();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {
            this.slidingMenu.toggle();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.slidingMenu.toggle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


