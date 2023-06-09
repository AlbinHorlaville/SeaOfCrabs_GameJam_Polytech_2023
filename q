[1mdiff --git a/game-project/game/info3/game/modele/Entity.java b/game-project/game/info3/game/modele/Entity.java[m
[1mindex 5d55a09..b4fe72f 100644[m
[1m--- a/game-project/game/info3/game/modele/Entity.java[m
[1m+++ b/game-project/game/info3/game/modele/Entity.java[m
[36m@@ -8,17 +8,13 @@[m [mpublic abstract class Entity {[m
 	  [m
 	  Avatar avatar;[m
 	  [m
[32m+[m	[32m  // Automaton Variable TODO[m[41m [m
[32m+[m	[32m  // State Variable TODO[m[41m [m
[32m+[m
[32m+[m[41m	  [m
 	  public Entity() {[m
 		// TODO Auto-generated constructor stub[m
 	  }[m
[31m-[m
[31m-	public int getX() {[m
[31m-		return x;[m
[31m-	}[m
[31m-[m
[31m-	public int getY() {[m
[31m-		return y;[m
[31m-	}[m
 	[m
 	public void initAvatar(Avatar avatar) {[m
 		this.avatar = avatar;[m
[36m@@ -32,11 +28,19 @@[m [mpublic abstract class Entity {[m
 	public Avatar getAvatar() {[m
 		return avatar;[m
 	}[m
[31m-	[m
[32m+[m
[32m+[m	[32mpublic int getX() {[m
[32m+[m		[32mreturn x;[m
[32m+[m	[32m}[m
[32m+[m
 	public void setX(int x) {[m
 		this.x = x;[m
 	}[m
[31m-	[m
[32m+[m
[32m+[m	[32mpublic int getY() {[m
[32m+[m		[32mreturn y;[m
[32m+[m	[32m}[m
[32m+[m
 	public void setY(int y) {[m
 		this.y = y;[m
 	}[m
[1mdiff --git a/game-project/game/info3/game/modele/GameModele.java b/game-project/game/info3/game/modele/GameModele.java[m
[1mindex 27bad50..4542e47 100644[m
[1m--- a/game-project/game/info3/game/modele/GameModele.java[m
[1m+++ b/game-project/game/info3/game/modele/GameModele.java[m
[36m@@ -25,6 +25,7 @@[m [mimport java.io.IOException;[m
 import info3.game.GameState;[m
 import info3.game.Sound;[m
 import info3.game.vue.GameView;[m
[32m+[m[32mimport info3.game.vue.view.PlayingView;[m
 [m
 public class GameModele {[m
 [m
[36m@@ -63,7 +64,7 @@[m [mpublic class GameModele {[m
 		if (currentState == GameState.Menu) {[m
 			setCurrentState(GameState.Jeu);[m
 			Cowboy cowboy = new Cowboy();[m
[31m-			this.gameview.inputAvatar(cowboy.getAvatar());[m
[32m+[m			[32mPlayingView.addAvatar(cowboy.getAvatar());[m
 		}[m
 	}[m
 [m
[1mdiff --git a/game-project/game/info3/game/vue/GameView.java b/game-project/game/info3/game/vue/GameView.java[m
[1mindex 930056f..78a8ffa 100644[m
[1m--- a/game-project/game/info3/game/vue/GameView.java[m
[1m+++ b/game-project/game/info3/game/vue/GameView.java[m
[36m@@ -158,8 +158,4 @@[m [mpublic class GameView {[m
 	private int m_musicIndex = 0;[m
 	private String[] m_musicNames = new String[] { "Runaway-Food-Truck" }; [m
 	[m
[31m-	public void inputAvatar(Avatar avatar) {[m
[31m-		((PlayingView) this.all_views.get(GameState.Jeu)).addAvatar(avatar);[m
[31m-	}[m
[31m-[m
 }[m
[1mdiff --git a/game-project/game/info3/game/vue/avatar/CowboyAvatar.java b/game-project/game/info3/game/vue/avatar/CowboyAvatar.java[m
[1mindex df096a4..9c7188b 100644[m
[1m--- a/game-project/game/info3/game/vue/avatar/CowboyAvatar.java[m
[1m+++ b/game-project/game/info3/game/vue/avatar/CowboyAvatar.java[m
[36m@@ -17,7 +17,6 @@[m [mpublic class CowboyAvatar extends Avatar {[m
 	 * Simple animation here, the cowbow [m
 	 */[m
 	public void tick(long elapsed) {[m
[31m-		System.out.println("TEST");[m
 		imageElapsed += elapsed;[m
 		if (imageElapsed > 200) {[m
 			imageElapsed = 0;[m
[1mdiff --git a/game-project/game/info3/game/vue/view/PlayingView.java b/game-project/game/info3/game/vue/view/PlayingView.java[m
[1mindex c2897b1..9c1ab6b 100644[m
[1m--- a/game-project/game/info3/game/vue/view/PlayingView.java[m
[1m+++ b/game-project/game/info3/game/vue/view/PlayingView.java[m
[36m@@ -8,7 +8,7 @@[m [mimport info3.game.vue.avatar.Avatar;[m
 [m
 public class PlayingView extends View{[m
 [m
[31m-	ArrayList<Avatar> avatars;[m
[32m+[m	[32mstatic ArrayList<Avatar> avatars;[m
 	[m
 	public PlayingView() throws IOException {[m
 		avatars = new ArrayList<>();[m
[36m@@ -28,8 +28,12 @@[m [mpublic class PlayingView extends View{[m
 		}[m
 	}[m
 	[m
[31m-	public void addAvatar(Avatar avatar){[m
[31m-		this.avatars.add(avatar);[m
[32m+[m	[32mpublic static void addAvatar(Avatar avatar){[m
[32m+[m		[32mPlayingView.avatars.add(avatar);[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32mpublic static void deleteAvatar(Avatar avatar) {[m
[32m+[m		[32mPlayingView.avatars.remove(avatar);[m
 	}[m
 	[m
 [m
