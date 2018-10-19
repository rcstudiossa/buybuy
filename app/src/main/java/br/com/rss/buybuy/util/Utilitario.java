package br.com.rss.buybuy.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.rss.buybuy.BuildConfig;
import br.com.rss.buybuy.R;

/**
 * Created by Roque Souza on 26/09/2018.
 */

public final class Utilitario {

    public static boolean isEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }

    public static boolean isEmpty(Object object) {
        return (object == null);
    }

    public static boolean isCNSValido(String s) {
        if (s.matches("[1-2]\\d{10}00[0-1]\\d") || s.matches("[7-9]\\d{14}")) {
            return somaPonderada(s) % 11 == 0;
        }
        return false;
    }

    public final static boolean isEmailValido(String txtEmail) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches();
    }

    private static int somaPonderada(String s) {
        char[] cs = s.toCharArray();
        int soma = 0;
        for (int i = 0; i < cs.length; i++) {
            soma += Character.digit(cs[i], 10) * (15 - i);
        }
        return soma;
    }

    public static String addAviso(String texto, String aviso) {

        texto = "" + texto;

        if (isEmpty(aviso)) {
            aviso = texto;
        } else {
            aviso = aviso + "\n" + texto;
        }

        return aviso;

    }

    public static String addCodigo(String texto, String valor) {

        if (isEmpty(texto)) {
            texto = valor;
        } else {
            texto = texto + "," + valor;
        }

        return texto;

    }

    public static String getDataHojeFormatada() {

        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    }

    public static String getDataFormatada(Date data) {

        if (Utilitario.isEmpty(data)) {
            return null;
        }

        return new SimpleDateFormat("dd/MM/yyyy").format(data);

    }

    public static Date getDate(String data) {

        if (Utilitario.isEmpty(data)) {
            return null;
        }

        try {
            //SimpleDateFormat formato = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void avisoSucesso(Context context) {

        Utilitario.aviso("Operação realizada com sucesso", context);

    }
    public static void aviso(String texto, Context context) {

        Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();

    }

    public static void alertar(Activity activity, String texto) {

        ViewDialog alert = new ViewDialog();
        alert.showDialog(activity, texto);

    }

    public static boolean dataValida(String data) {

        Date dataValida;
        SimpleDateFormat padrao = new SimpleDateFormat("dd/MM/yyyy");

        try {
            padrao.setLenient(false);
            dataValida = padrao.parse(data);

            if (dataValida.after(new Date())) {
                return false;
            }

            int meses = diferencaMeses(new Date(), dataValida);

            if (Math.abs(meses) > (130 * 12)) {
                return false;
            }


        } catch (ParseException e) {
            return false;
        }

        return true;

    }

    public static void exibirErro(View view, String msg) {

        if (view instanceof TextView) {
            ((TextView) view).setText(msg);
            ((TextView) view).setTextSize(13);
            ((TextView) view).setTextColor(Color.parseColor("#D50000"));

            view.setFocusable(true);
            view.requestFocus();
        } else if (view instanceof TextInputLayout) {
            ((TextInputLayout) view).setError(msg);
            view.requestFocus();
        } else if (view instanceof Button) {
            ((Button) view).setError(msg);
            view.requestFocus();
        }


    }

    public static void limparErros(View view) {

        View componente;

        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

            componente = ((ViewGroup) view).getChildAt(i);

            if (componente instanceof ViewGroup) {
                limparErros(componente);
            }

            if (componente instanceof TextView) {
                if (((TextView) componente).getCurrentTextColor() == Color.parseColor("#D60000")) {
                    ((TextView) componente).setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
                }
            } else if (componente instanceof TextInputLayout) {
                if (((TextInputLayout) componente).getError() != null) {
                    ((TextInputLayout) componente).setError(null);
                    ((TextInputLayout) componente).setErrorEnabled(false);
                    componente.clearFocus();
                }
            }
        }
    }

    public static String getVersao() {
        String versionName = BuildConfig.VERSION_NAME;
        return versionName;
    }

    public static int diferencaMeses(Date dataAtual, Date dataAnterior) {

        Calendar cDataAnterior = new GregorianCalendar();
        cDataAnterior.setTime(dataAnterior);

        Calendar cDataAtual = new GregorianCalendar();
        cDataAtual.setTime(dataAtual);

        return (cDataAtual.get(Calendar.YEAR) * 12 + cDataAtual.get(Calendar.MONTH)) - (cDataAnterior.get(Calendar.YEAR) * 12 + cDataAnterior.get(Calendar.MONTH));

    }

    public static boolean isNomeValido(String nome) {

        boolean valido = true;

        if (!isEmpty(nome)) {

            if (nome.trim().length() <= 3) {
                valido = false;
            }

            if (!nome.trim().contains(" ")) {
                valido = false;
            }

            String[] partes = nome.split(" ");
            int qtdAbreviacoes = 0;
            for (int i=0; i < partes.length; i++) {

                if (partes[i].trim().length() == 1) {
                    qtdAbreviacoes++;
                } else {
                    qtdAbreviacoes = 0;
                }

                if (qtdAbreviacoes >= 2) {
                    valido = false;
                }

            }


        }

        return valido;

    }

}
