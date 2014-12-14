package yn4_jjc7.client.model.message.chat.unknown;


import map.MapLayer;

public interface IModel2ViewAdapter {
	public void addPlace(Place p);
	public void show(MapLayer layer);
	public void hide(MapLayer layer);
}
