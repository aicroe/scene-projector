package org.example.framework;

import org.example.ui.UI;
import static org.example.ui.UI.FRONTAL;
import static org.example.ui.UI.SUPERIOR;
import static org.example.ui.UI.LATERAL;
import static org.example.ui.UI.PERSPECTIVE;
import static org.example.matlib.Transformations.R3D;
import static org.example.matlib.Transformations.S3D;
import static org.example.matlib.Transformations.T3D;
import static org.example.matlib.Point.X;
import static org.example.matlib.Point.Y;
import static org.example.matlib.Point.Z;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.example.matlib.Matrix;
import org.example.matlib.Point;
import org.example.matlib.Vector;

import static java.awt.event.KeyEvent.*;

import org.example.representation.PolygonMesh;
import org.example.representation.Vertex;

/**
 * 
 * @author diegogarcia
 *
 */
public class UIController implements KeyListener {
	
	public static final double THETA = 0.174532925;	// 10 grados 
	public static final double DELTA = 10.0;
	public static final double SCALAR = 1.2;
	
	final String H_OP = "Presiona: T traslacion, R rotar, S escalar";
	final String H_AX = "Presiona: X eje x, Y eje y, Z eje z";
	final String H_CH = "Presiona: LEFT (-), RIGHT (+) para ejecutar las operaciones";
	final String H_CV = "Presiona: DOWN, UP para mover la caja de mensajes";
	
	private int key;
	private Vector axis;
	private World world;
	private UI ui;
	private Observer[] observers;
	
	public UIController(UI _ui) {
		
		world = new World();
		ui = _ui;
		key = -1;
		observers = new Observer[4];
	}
	
	private PolygonMesh createObject() {

		PolygonMesh pm = new PolygonMesh();		
		//frente
		Vertex v1 = new Vertex(50, 50, 150);
		Vertex v2 = new Vertex(50, 200, 150);
		Vertex v3 = new Vertex(160, 200, 150);
		Vertex v4 = new Vertex(160, 50, 150);
		Vertex v5 = new Vertex(60, 60, 150);
		Vertex v6 = new Vertex(60, 130,150);
		Vertex v7 = new Vertex(100, 130, 150);
		Vertex v8 = new Vertex(100, 60, 150);
		Vertex v9 = new Vertex(110, 60, 150);
		Vertex v10 = new Vertex(110, 130, 150);
		Vertex v11 = new Vertex(150, 130, 150);
		Vertex v12 = new Vertex(150, 60, 150);
		Vertex v13 = new Vertex(60, 140, 150);
		Vertex v14 = new Vertex(60, 190, 150);
		Vertex v15 = new Vertex(150, 190, 150);
		Vertex v16 = new Vertex(150, 140, 150);
		
		pm.addVertex(v1);
		pm.addVertex(v2);
		pm.addVertex(v3);
		pm.addVertex(v4);
		pm.createEdge(v1.getIndex(), v2.getIndex());
		pm.createEdge(v2.getIndex(), v3.getIndex());
		pm.createEdge(v3.getIndex(), v4.getIndex());
		pm.createEdge(v4.getIndex(), v1.getIndex());
		pm.createPolygon(
				v1.getIndex(), v2.getIndex(), 
				v3.getIndex(), v4.getIndex());
		
		pm.addVertex(v5);
		pm.addVertex(v6);
		pm.addVertex(v7);
		pm.addVertex(v8);
		pm.createEdge(v5.getIndex(), v6.getIndex());
		pm.createEdge(v6.getIndex(), v7.getIndex());
		pm.createEdge(v7.getIndex(), v8.getIndex());
		pm.createEdge(v8.getIndex(), v5.getIndex());
		pm.createPolygon(
				v5.getIndex(), v6.getIndex(), 
				v7.getIndex(), v8.getIndex());
		
		pm.addVertex(v9);
		pm.addVertex(v10);
		pm.addVertex(v11);
		pm.addVertex(v12);
		pm.createEdge(v9.getIndex(), v10.getIndex());
		pm.createEdge(v10.getIndex(), v11.getIndex());
		pm.createEdge(v11.getIndex(), v12.getIndex());
		pm.createEdge(v12.getIndex(), v9.getIndex());
		pm.createPolygon(
				v9.getIndex(), v10.getIndex(), 
				v11.getIndex(), v12.getIndex());
		
		pm.addVertex(v13);
		pm.addVertex(v14);
		pm.addVertex(v15);
		pm.addVertex(v16);
		pm.createEdge(v13.getIndex(), v14.getIndex());
		pm.createEdge(v14.getIndex(), v15.getIndex());
		pm.createEdge(v15.getIndex(), v16.getIndex());
		pm.createEdge(v16.getIndex(), v13.getIndex());
		pm.createPolygon(
				v13.getIndex(), v14.getIndex(), 
				v15.getIndex(), v16.getIndex());
		
		//atras
		Vertex v17 = new Vertex(160, 50, 220);
		Vertex v18 = new Vertex(160, 200, 220);
		Vertex v19 = new Vertex(50, 200, 220);
		Vertex v20 = new Vertex(50, 50, 220);
		
		pm.addVertex(v17);
		pm.addVertex(v18);
		pm.addVertex(v19);
		pm.addVertex(v20);
		pm.createEdge(v17.getIndex(), v18.getIndex());
		pm.createEdge(v18.getIndex(), v19.getIndex());
		pm.createEdge(v19.getIndex(), v20.getIndex());
		pm.createEdge(v20.getIndex(), v17.getIndex());
		pm.createPolygon(
				v17.getIndex(), v18.getIndex(), 
				v19.getIndex(), v20.getIndex());
		
		//lateral derecho
		pm.createEdge(v4.getIndex(), v17.getIndex());
		pm.createEdge(v3.getIndex(), v18.getIndex());
		pm.createPolygon(
				v4.getIndex(), v3.getIndex(), 
				v18.getIndex(), v17.getIndex());
		
		//lateral izquierdo
		pm.createEdge(v1.getIndex(), v20.getIndex());
		pm.createEdge(v2.getIndex(), v19.getIndex());
		pm.createPolygon(
				v20.getIndex(), v19.getIndex(), 
				v2.getIndex(), v1.getIndex());
		
		//superior, inferior
		pm.createPolygon(
				v2.getIndex(), v19.getIndex(), 
				v18.getIndex(), v3.getIndex());
		pm.createPolygon(
				v1.getIndex(), v20.getIndex(), 
				v17.getIndex(), v4.getIndex());
		
		//caja inferior izquierda 
		//atras
		Vertex v21 = new Vertex(60, 60, 210);
		Vertex v22 = new Vertex(60, 130, 210);
		Vertex v23 = new Vertex(100, 130, 210);
		Vertex v24 = new Vertex(100, 60, 210);
		
		pm.addVertex(v21);
		pm.addVertex(v22);
		pm.addVertex(v23);
		pm.addVertex(v24);
		pm.createEdge(v21.getIndex(), v22.getIndex());
		pm.createEdge(v22.getIndex(), v23.getIndex());
		pm.createEdge(v23.getIndex(), v24.getIndex());
		pm.createEdge(v24.getIndex(), v21.getIndex());
		pm.createPolygon(
				v21.getIndex(), v22.getIndex(), 
				v23.getIndex(), v24.getIndex());
		
		//laterales
		//izquierda
		pm.createEdge(v5.getIndex(), v21.getIndex());
		pm.createEdge(v6.getIndex(), v22.getIndex());
		pm.createPolygon(
				v5.getIndex(), v6.getIndex(), 
				v22.getIndex(), v21.getIndex());
		//derecha
		pm.createEdge(v7.getIndex(), v23.getIndex());
		pm.createEdge(v8.getIndex(), v24.getIndex());
		pm.createPolygon(
				v24.getIndex(), v23.getIndex(), 
				v7.getIndex(), v8.getIndex());
		//superior, inferior caja
		pm.createPolygon(
				v5.getIndex(), v21.getIndex(), 
				v24.getIndex(), v8.getIndex());
		pm.createPolygon(
				v6.getIndex(), v22.getIndex(), 
				v23.getIndex(), v7.getIndex());
		
		//caja inferior derecha
		//atras
		Vertex v25 = new Vertex(110, 60, 210);
		Vertex v26 = new Vertex(110, 130, 210);
		Vertex v27 = new Vertex(150, 130, 210);
		Vertex v28 = new Vertex(150, 60, 210);
		
		pm.addVertex(v25);
		pm.addVertex(v26);
		pm.addVertex(v27);
		pm.addVertex(v28);
		pm.createEdge(v25.getIndex(), v26.getIndex());
		pm.createEdge(v26.getIndex(), v27.getIndex());
		pm.createEdge(v27.getIndex(), v28.getIndex());
		pm.createEdge(v28.getIndex(), v25.getIndex());
		pm.createPolygon(
				v25.getIndex(), v26.getIndex(), 
				v27.getIndex(), v28.getIndex());
		//laterales
		//izquierda
		pm.createEdge(v25.getIndex(), v9.getIndex());
		pm.createEdge(v26.getIndex(), v10.getIndex());
		pm.createPolygon(
				v9.getIndex(), v10.getIndex(), 
				v26.getIndex(), v25.getIndex());
		//derecha
		pm.createEdge(v28.getIndex(), v12.getIndex());
		pm.createEdge(v27.getIndex(), v11.getIndex());
		pm.createPolygon(
				v28.getIndex(), v27.getIndex(), 
				v11.getIndex(), v12.getIndex());
		//superior, inferior caja
		pm.createPolygon(
				v9.getIndex(), v25.getIndex(), 
				v28.getIndex(), v12.getIndex());
		pm.createPolygon(
				v10.getIndex(), v26.getIndex(), 
				v27.getIndex(), v11.getIndex());
		
		//caja superior
		Vertex v29 = new Vertex(60, 140, 210);
		Vertex v30 = new Vertex(60, 190, 210);
		Vertex v31 = new Vertex(150, 190, 210);
		Vertex v32 = new Vertex(150, 140, 210);
		pm.addVertex(v29);
		pm.addVertex(v30);
		pm.addVertex(v31);
		pm.addVertex(v32);
		pm.createEdge(v29.getIndex(), v30.getIndex());
		pm.createEdge(v30.getIndex(), v31.getIndex());
		pm.createEdge(v31.getIndex(), v32.getIndex());
		pm.createEdge(v32.getIndex(), v29.getIndex());
		pm.createPolygon(
				v29.getIndex(), v30.getIndex(), 
				v31.getIndex(), v32.getIndex());
		//laterales
		//izquierda
		pm.createEdge(v29.getIndex(), v13.getIndex());
		pm.createEdge(v30.getIndex(), v14.getIndex());
		pm.createPolygon(
				v13.getIndex(), v14.getIndex(), 
				v30.getIndex(), v29.getIndex());
		//derecha
		pm.createEdge(v32.getIndex(), v16.getIndex());
		pm.createEdge(v31.getIndex(), v15.getIndex());
		pm.createPolygon(
				v32.getIndex(), v31.getIndex(), 
				v15.getIndex(), v16.getIndex());
		//superior, inferior cajas
		pm.createPolygon(
				v30.getIndex(), v14.getIndex(),
				v15.getIndex(), v31.getIndex());
		pm.createPolygon(
				v13.getIndex(), v29.getIndex(), 
				v32.getIndex(), v16.getIndex());
		
		pm.setGravityCenter(new Point(105, 125, 185));
		return pm;
	}
	
	public void init() {

		observers[PERSPECTIVE] = new PerspectiveObserver(50, world);
		observers[FRONTAL] = new FrontalObserver(world);
		observers[SUPERIOR] = new SuperiorObserver(world);
		observers[LATERAL] = new LateralObserver(world);
		
		observers[PERSPECTIVE].setWindow(new Window(-20, -20, 80, 80));
		observers[FRONTAL].setWindow(new Window(0, 0, 300, 300));
		observers[SUPERIOR].setWindow(new Window(0, 0, 300, 300));
		observers[LATERAL].setWindow(new Window(0, 0, 300, 300));

		ui.matchViewport(UI.PERSPECTIVE, observers[PERSPECTIVE]);
		ui.matchViewport(UI.FRONTAL, observers[FRONTAL]);
		ui.matchViewport(UI.SUPERIOR, observers[SUPERIOR]);
		ui.matchViewport(UI.LATERAL, observers[LATERAL]);
		
		world.addObject(createObject());
		projectWorld();
		
		ui.showMessage(H_OP);
		ui.showMessage(H_AX);
		ui.showMessage(H_CH);
		ui.showMessage(H_CV);
	}
	
	private void projectWorld() {
		
		for (Observer o: observers) {
			o.projectWorld();
			o.updateGraphics();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		//vacio
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		//vacio
	}
	
	public boolean isLetter(int key) {
		
		return (key >= 'A' && key <= 'Z');
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		

		int keyCode = e.getKeyCode();
		if (keyCode == VK_UP) {
			ui.moveOffsetBox(-1);
			
		} else if (keyCode == VK_DOWN) {
			ui.moveOffsetBox(1);
			
		} else if (isLetter(keyCode)) {
			if (keyCode == VK_X) {
				axis = World.i;
				ui.showMessage("Eje X Seleccionado"); 
			} else if (keyCode == VK_Y) {
				axis = World.j;
				ui.showMessage("Eje Y Seleccionado");
			} else if (keyCode == VK_Z) {
				axis = World.k;
				ui.showMessage("Eje Z Seleccionado");
			} else {
				key = keyCode;
				ui.showMessage("Tecla Presionada: " + (char)keyCode);
			}
			
		} else if (axis != null){
			Matrix m;
			if (key == VK_T) {
				if (keyCode == VK_LEFT) {
					double dx = axis.get(X) * -DELTA;
					double dy = axis.get(Y) * -DELTA;
					double dz = axis.get(Z) * -DELTA;
					m = T3D(dx, dy, dz);
					String msg = String.format(
							"Traslasion: dx=%d dy=%d dz=%d", 
							(int)dx, (int)dy, (int)dz);
					ui.showMessage(msg);
				} else if (keyCode == VK_RIGHT) {
					double dx = axis.get(X) * DELTA;
					double dy = axis.get(Y) * DELTA;
					double dz = axis.get(Z) * DELTA;
					m = T3D(dx, dy, dz);
					String msg = String.format(
							"Traslasion: dx=%d dy=%d dz=%d", 
							(int)dx, (int)dy, (int)dz);
					ui.showMessage(msg);
					
				} else {
					ui.showMessage(H_CH);
					ui.repaint();
					return;
				}
				world.apply(m);
				
			} else if (key == VK_R) {
				if (keyCode == VK_LEFT) {
					m = R3D(axis, -THETA);
					String msg = String.format(
							"Rotacion: theta=%f", -THETA);
					ui.showMessage(msg);
				} else if (keyCode == VK_RIGHT) {
					m = R3D(axis, THETA);
					String msg = String.format(
							"Rotacion: theta=%f", THETA);
					ui.showMessage(msg);
				} else {
					ui.showMessage(H_CH);
					ui.repaint();
					return;
				}
				world.applyRelatedToCenter(m);
				
			} else if (key == VK_S) {
				if (keyCode == VK_LEFT) {
					m = S3D(axis, 1.0/SCALAR);
					String msg = String.format(
							"Escalado: k=%f", 1.0/SCALAR);
					ui.showMessage(msg);
				} else if (keyCode == VK_RIGHT) {
					m = S3D(axis, SCALAR);
					String msg = String.format(
							"Escalado: k=%f", SCALAR);
					ui.showMessage(msg);
				} else {
					ui.showMessage(H_CH);
					ui.repaint();
					return;
				}
				world.apply(m);
				
			} else {
				ui.showMessage(H_OP);
				ui.repaint();
				return;
			}
			projectWorld();
		} else {
			ui.showMessage(H_OP);
			ui.showMessage(H_AX);
		}
		ui.repaint();
	}

}
