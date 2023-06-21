package info3.game.modele.StillEntityClass;

import java.util.ArrayList;
import java.util.Iterator;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.avatar.CloudAvatar;

public class CloudCluster extends Entity {

	private ArrayList<Cloud> cluster;

	private static final int NB_CLOUD = 10;
	private static final int SIZE_CLOUDCLSUTER = 400;
	private static final int SIZE_CLOUD_MIN = 6;
	private static final int SIZE_CLOUD_MAX = 12;

	/**
	 * Créer un amas de nuages composés de NB_CLOUD nuages. Il y a 2 nuages par
	 * tailles, de 1 à 5. Les nuages sont équitablement répartie autour des
	 * coordonnées du centre de l'amas. Quand le joueur touche un nuage, celui-ci
	 * deisparaît. l'usage d'un boulet de canon dissipe aussi le nuage. ATTENTION NE
	 * PAS ADD A ENTITY LE CLOUDCLUSTER
	 */
	public CloudCluster(int x, int y) {
		super(x, y);
		this.cluster = new ArrayList<Cloud>();
		for (int i = NB_CLOUD; i > 0; i--) {
			// Génère une position aléatoire dans un carré de 200 par 200 à partir des
			// coordonnées de CloudCluster
			int posX = (int) (java.lang.Math.random() * SIZE_CLOUDCLSUTER + getX());
			int posY = (int) (java.lang.Math.random() * SIZE_CLOUDCLSUTER + getY());
			int size = (int) (java.lang.Math.random() * (SIZE_CLOUD_MAX - SIZE_CLOUD_MIN) + SIZE_CLOUD_MIN);
			Cloud Current = new Cloud(size, posX, posY, this); // sizeCloud>0 ? sizeCloud+1 : -sizeCloud+1
			Current.setAvatar(new CloudAvatar(Current, Current.getSize()));
			cluster.add(Current);
		}
	}

	public ArrayList<Cloud> getClouds() {
		return this.cluster;
	}
	
	public void step() {
	}

	public int getNbCloud() {
		return NB_CLOUD;
	}
}
