package org.annotopia.grails.connectors.plugin.client.annotatorjs.encoding

import grails.converters.JSON

import org.apache.jena.riot.RDFDataMgr
import org.apache.jena.riot.RDFLanguages
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.Before
import org.junit.Test

import com.hp.hpl.jena.query.Dataset
import com.hp.hpl.jena.rdf.model.Model

class AnnotatorJsStorageTests extends GroovyTestCase {

	def annotatorJsEncoderService;
	def annotatorJsStorageService;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testSimpleAnnotationCreation001() {
		def json = '{"tags":[],"text":"safsafsa","quote":"hall","ranges":[{"endOffset":16,"start":"/div[1]/h4[1]","end":"/div[1]/h4[1]","startOffset":12}],"permissions":{"update":[],"admin":[],"delete":[],"read":[]},"uri":"http://afdemo.aws.af.cm/annotator/index","user":"jmiranda", "id":"32423432"}';
		
		JSONObject jsonObject = JSON.parse(json);
		
		Model model = annotatorJsEncoderService.encode(jsonObject)
		assertNotNull(model);
		
		log.info "==========================="
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		RDFDataMgr.write(outputStream, model, RDFLanguages.JSONLD);
		log.info outputStream.toString();
		
		log.info "==========================="
		
		Dataset set = null;
		try {
			log.info("start: " +set)
			set = annotatorJsStorageService.save("testkey", System.currentTimeMillis(), model);
			log.info("after: " +set)
			outputStream = new ByteArrayOutputStream();
			RDFDataMgr.write(outputStream, set, RDFLanguages.JSONLD);
			log.info outputStream.toString();
		} catch(Exception e) {
			log.error "Error:" + e.getMessage(),e;
		}
		assertNotNull(set);
	}
}
