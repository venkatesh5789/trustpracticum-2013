package test;


import edu.cmu.dataset.*;
import edu.cmu.DBLPProcessor.*;
import edu.cmu.jung.*;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.algorithms.layout.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.xml.bind.JAXBException;

import org.apache.commons.collections15.Transformer;

public class MyFirstS extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MyFirstS() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     PrintWriter pw = response.getWriter();
     pw.println("OK");
     pw.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String name = request.getParameter("name");
			String level = request.getParameter("level");
			int l = Integer.parseInt(level);
			ServletContext context = getServletContext();
			String fullPath = context.getRealPath("/WEB-INF/dblp_example.xml");
			//////////////////////////////////////	
			UserCoAuthorSubgraph myApp = new UserCoAuthorSubgraph(fullPath);
			String result = null;			

			try {
				result = myApp.constructGraph(name,l);
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//myApp.constructGraph("Javier Chorro",1);		
			// This builds the graph
			Layout<Node, Edge> layout = new CircleLayout<Node, Edge>(myApp.g);
			layout.setSize(new Dimension(650,650));
			VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
			vv.setPreferredSize(new Dimension(700,700));       

			// Setup up a new vertex to paint transformer...
			Transformer<Node,Paint> vertexPaint = new Transformer<Node,Paint>() {
				public Paint transform(Node e) {
					if(e.getLevel() == 0)
						return Color.GREEN;
					else
						return Color.RED;
				}
			};  

			// Set up a new stroke Transformer for the edges
			float dash[] = {10.0f};
			final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
					Transformer<Edge, Stroke> edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
						public Stroke transform(Edge e) {
							return edgeStroke;
						}
					};	

			vv.setVertexToolTipTransformer(new Transformer<Node, String>() {
				public String transform(Node e) {
					return "Name: " + e.getUser().getName() ;
				}
			});
			
			vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
			//vv.getRenderContext().setEdgeArrowStrokeTransformer(edgeStroke);
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);        
			JFrame frame = new JFrame("Co-authorship Graph View");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(vv);
			frame.pack();
			frame.setVisible(true); 
			
			/////////////////////////////////////	
			PrintWriter pw = response.getWriter();
			pw.println(result+"\n");
			pw.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
