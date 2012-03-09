package fr.generali.gwt.dispatch.client.fwk;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

/**
 * Permet de construire une URL d'appel vers le dispatcher à partir d'une
 * action.
 * 
 * Le but est de permettre d'enrichir les URL avec des informations distinctives
 * afin de faire un filtrage d'URL (pour tracer les appels vers le serveur,
 * faire un filtrage de sécurité, etc.)
 * 
 * @author M. Abdennebi
 * 
 */
public interface DispatchUrlBuilder {

	<R extends Result> String buildURL(Action<R> action);
}