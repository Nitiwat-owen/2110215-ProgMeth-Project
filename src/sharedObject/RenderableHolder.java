package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image mapSprite;
	public static Image tankSprite;
	public static AudioClip timerSound, explosionSound, bulletSound, penetBulletSound, outOfWeaponSound,
			bulletHittingSound, penetBulletHittingSound, backgroundMusic;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
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
		//backgroundMusic = new AudioClip(ClassLoader.getSystemResource("gameBackgroundMusic.wav").toString());
		timerSound = new AudioClip(ClassLoader.getSystemResource("Timer.wav").toString());
		explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
		bulletSound = new AudioClip(ClassLoader.getSystemResource("bulletSound.wav").toString());
		penetBulletSound = new AudioClip(ClassLoader.getSystemResource("penetratedBulletSound.wav").toString());
		outOfWeaponSound = new AudioClip(ClassLoader.getSystemResource("bulletEmpty.wav").toString());
		bulletHittingSound = new AudioClip(ClassLoader.getSystemResource("bulletHitWall.wav").toString());
		penetBulletHittingSound = new AudioClip(ClassLoader.getSystemResource("penetBulletHitWall.wav").toString());
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
