package controllers;

import javax.xml.bind.JAXBException;

import edu.cmu.jung.Edge;
import edu.cmu.jung.UserCoAuthorSubgraph;
import edu.uci.ics.jung.algorithms.layout.Layout;
import play.*;
import play.data.*;
import play.data.Form.*;
import play.data.validation.Constraints.Required;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.xml.bind.JAXBException;

import org.apache.commons.collections15.Transformer;
import org.json.JSONArray;
import org.json.JSONObject;

import edu.cmu.DBLPProcessor.Coauthorship;
import edu.cmu.DBLPProcessor.DBLPParser;
import edu.cmu.DBLPProcessor.DBLPUser;
import edu.cmu.dataset.DBLPDataSource;
import edu.cmu.dataset.DatasetInterface;
import edu.cmu.jung.Node;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render(""));
	}

	public static class Show {
		@Required public String name;
		@Required public Integer level;
		public Integer renderGraph;
	}

	public static JSONArray myGraph(String name, Integer level, Integer renderGraph) throws JAXBException {
		UserCoAuthorSubgraph myApp = new UserCoAuthorSubgraph();
		JSONArray result;

		result = myApp.constructGraph(name,level);;

		//System.out.println(result);

		// This builds the graph
		if(renderGraph != 0) {
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

		}

		return result;

	}
	
	public static Result generateDummyJson(String name, Integer level) {
//		JSONArray resultJson = new JSONArray();
//		
//		//ArrayList<JSONObject> singleEdge = new ArrayList<JSONObject>();
//		JSONObject[] singleEdge = new JSONObject[5];
//		
//		singleEdge[0].put("startingNode", "Javier Chorro");
//		singleEdge[0].put("endingNode", "Julio Gomis-Tena");
//		singleEdge[1].put("startingNode", "Javier Chorro");
//		singleEdge[1].put("endingNode", "Marta Monserrat");
//		singleEdge[2].put("startingNode", "Javier Chorro");
//		singleEdge[2].put("endingNode", "Javier Saiz");
//		singleEdge[3].put("startingNode", "Javier Chorro");
//		singleEdge[3].put("endingNode", "Jose Maria Ferrero");
//		singleEdge[4].put("startingNode", "Javier Chorro");
//		singleEdge[4].put("endingNode", "Karen Cardona");
//	
//		for(int i = 0; i<5; i++)
//			resultJson.put(singleEdge[i]);
		return ok(show.render("[{\"endingNode\":\"Julio Gomis-Tena\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Marta Monserrat\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Javier Saiz\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Jose Maria Ferrero\",\"startingNode\":\"Javier Chorro\"},{\"endingNode\":\"Karen Cardona\",\"startingNode\":\"Javier Chorro\"}]"));
	}

	public static Result formSubmit() throws JAXBException{
		Form<Show> form = Form.form(Show.class).bindFromRequest();
		if(form.hasErrors()) {
			return badRequest(index.render("Errors in form"));
		} else {
			Show data = form.get();
			return ok(
					show.render(myGraph(data.name, data.level, data.renderGraph).toString())
					);
		}
	}

	public static Result getGraphWithRender(String name, Integer level) throws JAXBException{
		return ok(
				show.render(myGraph(name, level, 1).toString())
				);
	}
	
	public static Result getGraphWithoutRender(String name, Integer level) throws JAXBException{
		return ok(
				show.render(myGraph(name, level, 0).toString())
				);
	}
	
}
