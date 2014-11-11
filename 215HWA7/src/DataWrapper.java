import java.util.*;

// this silly little class is just a wrapper for a key, data pair
class DataWrapper<Key, MyData> {

	Key key;
	MyData data;

	public DataWrapper(Key keyIn, MyData dataIn) {
		key = keyIn;
		data = dataIn;
	}

	public Key getKey() {
		return key;
	}

	public MyData getData() {
		return data;
	}


}
