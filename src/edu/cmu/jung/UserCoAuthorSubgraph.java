package edu.cmu.jung;

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

/**
 *
 * @author NASA-Trust-team
 */

public class UserCoAuthorSubgraph {

	static int edgeCount = 0;  
	Graph<Node, Edge> g;
	//DirectedGraph<Node, Edge> g;
	List<Node> nodes = new ArrayList<Node>();
	HashMap<String,DBLPUser> dblp;

	public UserCoAuthorSubgraph() {   
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset();		
	}

	/** Constructs an example directed graph with our vertex and edge classes 
	 * @throws JAXBException */
	public String constructGraph(String name, int noOfLevels) throws JAXBException {
		g = new SparseMultigraph<Node, Edge>();
		//g = new DirectedSparseMultigraph<Node, Edge>();
		createNodes(name, noOfLevels);
		String result = createEdges();  
		return result;
	}

	private void createNodes(String name, int noOfLevels) throws JAXBException {
		String key = name;	

		Queue<Node> nodesQueue = new LinkedList<Node>();
		DBLPUser inputAuthor = dblp.get(key);
		System.out.println(inputAuthor.getId());
		Node currentNode = new Node(inputAuthor);
		currentNode.setLevel(0);
		nodes.add(currentNode);
		nodesQueue.add(currentNode);

		while(!nodesQueue.isEmpty() && noOfLevels > 0 ) {
			Node r = nodesQueue.remove();
			DBLPUser currentAuthor = r.getUser();
			List<Coauthorship> c = currentAuthor.getCoAuthors();

			for(int i =0;i<c.size();i++){
				for(Entry<String, Integer> entry : DBLPParser.mapUserNameId.entrySet()){
					if(entry.getValue() == c.get(i).getCoauthorid()){
						DBLPUser coauthor = dblp.get(entry.getKey());
						Node coauthorNode = new Node(coauthor);

						if(!nodes.contains(coauthorNode)) {
							nodes.add(coauthorNode);
							nodesQueue.add(coauthorNode);
						}
					}
				}
			}	
			noOfLevels--;
		}

		/*List<Coauthorship> c = inputAuthor.getCoAuthors();
			for(int i =0;i<c.size();i++){
				for(Entry<String, Integer> entry : DBLPParser.mapUserNameId.entrySet()){
					if(entry.getValue() == c.get(i).getCoauthorid()){
						DBLPUser coauthor = dblp.get(entry.getKey());
						Node coauthorNode = new Node(coauthor);
						nodes.add(coauthorNode);
					}
				}
			}*/		
	}

	private String createEdges() throws JAXBException {
		int startingNodeNumber = 10;
		String result = "";
		
		for(int j = 0; j<nodes.size(); j++){
			String key = nodes.get(j).getUser().getName();;
			DBLPUser author = dblp.get(key);
			startingNodeNumber = getNodeFromAuthor(author);		
			List<Coauthorship> c = author.getCoAuthors(); 

			for(int i =0;i<c.size();i++){
				for(Entry<String, Integer> entry : DBLPParser.mapUserNameId.entrySet()){
					if(entry.getValue() == c.get(i).getCoauthorid()){
						int endingNodeNumber;
						DBLPUser coauthor = dblp.get(entry.getKey());
						endingNodeNumber = getNodeFromAuthor(coauthor);	
						g.addEdge(new Edge(),nodes.get(startingNodeNumber), nodes.get(endingNodeNumber), EdgeType.DIRECTED);
						result += nodes.get(startingNodeNumber).getUser().getName() + "\t" + nodes.get(endingNodeNumber).getUser().getName() + "\n";
					}
				}
			}
		}	
		return result;
	}

	private int getNodeFromAuthor(DBLPUser author) {
		for(int i = 0; i<nodes.size(); i++)
			if(nodes.get(i).isThisPassedNode(author))
				return i;

		return 0;
	}

	/**
	 * @param args the command line arguments
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws JAXBException {
		UserCoAuthorSubgraph myApp = new UserCoAuthorSubgraph();
		String inputAuthor;
		int noOfLevels;
		String result;
		
		Scanner input = new Scanner(System.in);

		System.out.println("\nEnter author's name- ");
		inputAuthor = input.nextLine();

		System.out.println("Enter number of sub levels- ");
		noOfLevels = input.nextInt();

		result = myApp.constructGraph(inputAuthor, noOfLevels);
		
		System.out.println(result);
		
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
		
	}

}
