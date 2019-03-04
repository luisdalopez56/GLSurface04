package com.videos.luisdalopez56.glsurface04;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class GLSurface extends GLSurfaceView {
    Renderizado miRender;

    public GLSurface(Context context) {
        super(context);
        miRender = new Renderizado(context);
        setRenderer(miRender);
    }


}

class Renderizado implements GLSurfaceView.Renderer {

    Cuadrado2 cuadrado1, cuadrado2, cuadrado3;
    private float movimiento;
    Context micontexto;

    float color1[] = {
        1.0f, 1.0f, 0.0f, 0.5f,
        0.0f, 1.0f, 1.0f, 0.5f,
        0.0f, 0.0f, 0.0f, 0.5f,
        1.0f, 0.0f, 1.0f, 0.5f
        };

    float color2[] = {
        1.0f, 0.0f, 0.0f, 0.5f,
        0.0f, 1.0f, 0.0f, 0.5f,
        0.0f, 0.0f, 1.0f, 0.5f,
        1.0f, 1.0f, 1.0f, 0.5f
        };

    public Renderizado(Context contexto) {
        micontexto=contexto;
        cuadrado1 = new Cuadrado2(color1);
        cuadrado2 = new Cuadrado2(color2);
        cuadrado3 = new Cuadrado2(color2);

    }


    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepthf(1.0f);
        //gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL11.GL_BLEND);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);

        cuadrado1.cargaTextura(gl,micontexto,R.drawable.mascara);
        cuadrado2.cargaTextura(gl,micontexto,R.drawable.textura4d);
        cuadrado3.cargaTextura(gl, micontexto, R.drawable.foto2b);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        if (alto == 0) alto = 1;
        float aspecto = (float) ancho / alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45, aspecto, 0.1f, 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glClearColor(0.0f,0.0f,0.0f,1.0f);
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        //gl.glColor4f(1.0f,1.0f,1.0f,1.0f);
        //gl.glEnableClientState(GL11.GL_COLOR_ARRAY);

        gl.glLoadIdentity();
        //gl.glTranslatef(0.0f,0.0f, -6.0f);
        //gl.glTranslatef(0.0f,(float)Math.sin(movimiento), -6.0f);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);
        gl.glTranslatef(0.0f,0.0f, -6.0f);
        cuadrado1.draw(gl);

        gl.glLoadIdentity();
        //gl.glTranslatef(0.5f,-0.5f, -6.0f);
        //gl.glTranslatef( (float)(Math.sin(movimiento)/2.0f),0.0f, -6.0f);
        gl.glBlendFunc(GL10.GL_DST_COLOR, GL10.GL_ZERO);
        gl.glTranslatef(0.0f,0.0f, -6.0f);
        gl.glColor4f(1.0f,1.0f,1.0f,0.5f);
        cuadrado2.draw(gl);

        gl.glLoadIdentity();
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);
        gl.glTranslatef(0.0f,0.0f, -6.0f);
        gl.glColor4f(1.0f,1.0f,1.0f,0.5f);

        cuadrado3.draw(gl);

        movimiento += .050f;
    }


}


