package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entity.Player;
import entity.SteelWall;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameController;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities;
	private List<IRenderable> backgroundEntities;
	private List<IRenderable> weaponEntities;
	private Comparator<IRenderable> comparator;
	public static Image mapSprite;
	public static Image tankSprite;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		backgroundEntities = new ArrayList<IRenderable>();
		weaponEntities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		mapSprite = new Image(ClassLoader.getSystemResource("image.png").toString());
		tankSprite = new Image(ClassLoader.getSystemResource("tankImage.png").toString());
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void addBackground(IRenderable entity) {
		backgroundEntities.add(entity);
		Collections.sort(backgroundEntities, comparator);
	}

	public void addWeapon(IRenderable entity) {
		weaponEntities.add(entity);
		Collections.sort(weaponEntities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public List<IRenderable> getBackground() {
		return backgroundEntities;
	}

	public List<IRenderable> getWeapon() {
		return weaponEntities;
	}
}
