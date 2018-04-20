package com.t2i.actualite.commun.rules;
//package com.t2i.actualite.commun.commun.rules;
//
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.t2i.actualite.commun.commun._config.CommonTestConfig;
//import com.t2i.actualite.commun.commun.test.junit.SpringSerenityUnitTest;
//
///**
// * @author Fenonantenaina
// *
// */
//@Transactional
//@ActiveProfiles("tests")
//@ContextConfiguration(classes=CommonTestConfig.class)
//public class RuleTest extends SpringSerenityUnitTest{
//
//	private enum StatusPrix{
//		BON_AFFAIRE,
//		MOYEN,
//		COUTEUX
//	}
//	
//	/**
//	 * @author Fenonantenaina
//	 *
//	 */
//	private class Article{
//		
//		private int prix;
//		private int quantite;
//		private String designation;
//		
//		public int getPrix() {
//			return prix;
//		}
//		public void setPrix(int prix) {
//			this.prix = prix;
//		}
//		public String getDesignation() {
//			return designation;
//		}
//		public void setDesignation(String designation) {
//			this.designation = designation;
//		}
//		public int getQuantite() {
//			return quantite;
//		}
//		public void setQuantite(int quantite) {
//			this.quantite = quantite;
//		}
//	}
//	
//	private class PriceRule extends RuleDecision<Article, StatusPrix>{
//
//		public PriceRule() {
//			super(StatusPrix.BON_AFFAIRE);
//		}
//		@Override
//		protected StatusPrix makeDecision(Article article) throws Exception {
//			StatusPrix status = StatusPrix.BON_AFFAIRE;
//			if(article.getPrix() > 10 && article.getPrix() < 20) status = StatusPrix.MOYEN;
//			else if(article.getPrix() >= 20) status = StatusPrix.COUTEUX;
//			return status;
//		}
//		
//	}
//	
//	
//	
//	private class PrixCouteuxRule extends SimpleRule<Article>{
//
//		public PrixCouteuxRule(AbstractComponent<Article> positiveRule,
//				AbstractComponent<Article> negativeRule) {
//			super(positiveRule, negativeRule);
//		}
//
//		@Override
//		protected boolean makeDecision(Article article) throws Exception {
//			PriceRule rule = new PriceRule();
//			rule.execute(article);
//			return rule.getStatus() == StatusPrix.COUTEUX;
//		}
//
//		
//	}
//	
//	private class EnvoiCommandeRule extends AbstractComponent<Article>{
//
//		@Override
//		public void execute(Article art) throws Exception {
//			String msg = "Il faut envoyer la commande pour l'Article " + art.getDesignation();
//			//On envoit la commande et transferer 10 unités du dépot B vers le magasin
//			art.setQuantite(art.getQuantite() + 10);
//		}
//		
//	}
//	private class StockRule extends RuleAction<Article>{
//
//		@Override
//		protected void doExecute(Article art) throws Exception {
//			if(art.quantite == 0){
//				this.setNextAction(new EnvoiCommandeRule());
//			}
//			else this.setNextAction(null);
//		}
//		
//	}
//	
//	@Test
//	public void verifierLaRegleSurLePrixArticle() throws Exception{
//		Article art = new Article();
//		art.setDesignation("Akondro");
//		art.setPrix(25);
//		PriceRule priceRule = new PriceRule();
//		priceRule.execute(art);
//		Assert.assertEquals(StatusPrix.COUTEUX, priceRule.getStatus());
//		
//		art.setPrix(8);
//		priceRule.execute(art);
//		Assert.assertEquals(StatusPrix.BON_AFFAIRE, priceRule.getStatus());
//	}
//	
//	@Test
//	public void verifierLaReductionDePrixArticleSiSuperieurA30() throws Exception{
//		
//		PrixCouteuxRule priceRule = new PrixCouteuxRule(new AbstractComponent<RuleTest.Article>() {
//			//Si prix couteux, on Prix - 6
//			public void execute(Article article) throws Exception {
//				article.setPrix(article.getPrix() - 6);
//			}
//		}, null);
//		
//		Article art = new Article();
//		art.setDesignation("Akondro");
//		art.setPrix(25);
//		priceRule.execute(art);
//		Assert.assertEquals(25-6, art.getPrix());
//		
//		art.setPrix(58);
//		priceRule.execute(art);
//		Assert.assertEquals(58-6, art.getPrix());
//		
//		art.setPrix(13);
//		priceRule.execute(art);
//		Assert.assertNotEquals(13, art.getPrix());
//	}
//	
//	@Test
//	public void verifierLaRegleSurLeQuantiteArticle() throws Exception{
//		
//		StockRule stockRule = new StockRule();
//		
//		Article art = new Article();
//		art.setDesignation("Akondro");
//		art.setPrix(25);
//		art.setQuantite(15);
//		stockRule.execute(art);
//		Assert.assertEquals(15, art.getQuantite());
//		
//		art.setQuantite(0);
//		stockRule.execute(art);
//		Assert.assertEquals(10, art.getQuantite());
//		
//		stockRule.execute(art);
//		Assert.assertEquals(10, art.getQuantite());
//	}
//	
//}
