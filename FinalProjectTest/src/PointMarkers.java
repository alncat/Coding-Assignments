
import java.awt.Color;
import java.awt.Font;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

public class PointMarkers extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame()
        {
            super(true, true, false);
           //Creating positions where the placemarkers will be placed.
           Position pointPosition = Position.fromDegrees(50, 7.5);
           Position pointPosition2 = Position.fromDegrees(51, 7.5);
           Position pointPosition3 = Position.fromDegrees(52, 7.5);
           //Creating markers providing the positions previously defined
           PointPlacemark pmStandard = new PointPlacemark(pointPosition);
           PointPlacemark pmRed = new PointPlacemark(pointPosition2);
           PointPlacemark pmBlue = new PointPlacemark(pointPosition3);
           //Creating new placemark attributes
           PointPlacemarkAttributes pointAttributeBlue = new PointPlacemarkAttributes();
           PointPlacemarkAttributes pointAttributeRed = new PointPlacemarkAttributes();
           //Attaching the placemark attributes to the placemarks.
           pmRed.setAttributes(pointAttributeRed);
           pmBlue.setAttributes(pointAttributeBlue);
           //Changing color of the placemarkers attached to their respective
           //placemark attribute.
           pointAttributeRed.setImageColor(Color.red);
           pointAttributeBlue.setImageColor(Color.blue);
           //Changing font type, size and setting it to bold.
           pointAttributeBlue.setLabelFont(Font.decode("Verdana-Bold-22"));
           //Changing the label text color
           pointAttributeBlue.setLabelMaterial(Material.CYAN);
           //Changing the text scale of the placemark 'pmBlue'
           //pointAttributeBlue.setLabelScale(1.8);
           //Setting text label for placemarkers.
           pmStandard.setLabelText("Standard placemark.");
           pmBlue.setLabelText("Font: Verdana, Size: 22, Bold.");
           //Setting up annotation pop-up to be activated with mouse-over
           //at the placemark 'point2'.
           pmRed.setValue(AVKey.DISPLAY_NAME, "Text Displayed by mouse-over");
           //Creating renderable layer to attach the markers.
           RenderableLayer layer = new RenderableLayer();
           //Adding placemarkers to the rederable layer
           layer.addRenderable(pmStandard);
           layer.addRenderable(pmRed);
           layer.addRenderable(pmBlue);
           //Adding renderable layer to the application.
           insertBeforeCompass(getWwd(), layer);
           this.getLayerPanel().update(this.getWwd());
        }
    }

    public static void main(String[] args)
    {
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 52);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 10);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 150e4);

    	ApplicationTemplate.start("NASA World Wind Tutorial - Placemarks", AppFrame.class);
    }
}