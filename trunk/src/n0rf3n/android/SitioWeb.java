package n0rf3n.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SitioWeb extends Activity {

	private WebView mWebView;

	public void onCreate(Bundle savedInstancestate) {
		super.onCreate(savedInstancestate);
		setContentView(R.layout.sitio_wed);

		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setPluginsEnabled(true);
		mWebView.getSettings().setSupportZoom(true);
		mWebView.loadUrl("https://code.google.com/p/benchmarkingmovil/");
		mWebView.setWebViewClient(new verSitio());

	}

	private class verSitio extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.MnuSalir:
			finish();
			System.exit(0);
			return true;
		case R.id.MnuSobre:
			Dialog dialogo;

			dialogo = crearDialogoAlerta(
					"Acerca de..",
					"BenchMarking Móvil Versión 1 \n \n Esta aplicación es de uso académico..!! \n \n Desarrollada por: \n Ing. Diego Ariza \n diego.ariza@ieee.org \n @n0rf3n \n \n Docentes - CoAutores \n Ph.D. Juan D. López \n Ing. Frank Romero \n \n Colombia - Bogotá \n 2012 \n \n http://benchmarkingmovil.googlecode.com/  \n",
					"Aceptar");
			dialogo.show();

			return true;
		case R.id.MnuSitioweb:

			Intent ventana = new Intent("n0rf3n.android.SitioWeb");
			startActivity(ventana);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Dialog crearDialogoAlerta(String titulo, String mensaje,
			String boton) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(titulo);
		builder.setMessage(mensaje);

		builder.setPositiveButton(boton, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}

		});
		return builder.create();
	}

}
