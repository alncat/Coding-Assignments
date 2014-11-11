import java.util.*;

// this silly little class is just a wrapper for a key, data pair and Leaf node
class SubInternalNode<Key extends IPointInMetricSpace<Key>, MyData, SubLeafNode> {

	Key key;
	MyData data;
	SubLeafNode kData;
	private ArrayList<SubLeafNode> getSubLeafNode, calMaxRadius;
	private SubLeafNode compareKey;

	public SubInternalNode(Key keyIn, MyData dataIn, SubLeafNode kdataIn) {
		key = keyIn;
		data = dataIn;
		kData = kdataIn;
	}

	public Key getKey() {
		return key;
	}

	// returns a leaf node
	public ArrayList<SubLeafNode> getSubLeafNode() {
		getSubLeafNode = new ArrayList<SubLeafNode>();
		getSubLeafNode.add(kData);
		return getSubLeafNode;
	}

	public MyData getData() {
		return data;
	}


}
