/*
 * Copyright 2014 Massachusetts General Hospital
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.annotopia.grails.connectors.plugin.client.annotatorjs.encoding;

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.Test

import com.hp.hpl.jena.query.QueryExecution
import com.hp.hpl.jena.query.QueryExecutionFactory
import com.hp.hpl.jena.query.QueryFactory
import com.hp.hpl.jena.query.QuerySolution
import com.hp.hpl.jena.query.ResultSet
import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.RDFNode
import com.hp.hpl.jena.rdf.model.Resource

//TODO test case with tags

/**
 * Testing the converter from the standard annotator.js format to the 
 * normalized Open Annotation format.
 * 
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class AnnotatorJsEncoderTest {

	@Test
	void normalizeTest() {
		def json = '{"tags":[],"text":"safsafsa","quote":"hall","ranges":[{"endOffset":16,"start":"/div[1]/h4[1]","end":"/div[1]/h4[1]","startOffset":12}],"permissions":{"update":[],"admin":[],"delete":[],"read":[]},"uri":"http://afdemo.aws.af.cm/annotator/index","user":"jmiranda", "id":"32423432"}';
		
		JSONObject jsonObject = JSON.parse(json);

		AnnotatorJsEncoder a = new AnnotatorJsEncoder();
		Model model = a.encode(jsonObject);
		
		try {
			String QUERY = "SELECT ?s ?p ?o WHERE { { ?s ?p ?o .}}";
			QueryExecution queryExecution  = QueryExecutionFactory.create (QueryFactory.create(QUERY), model);
			ResultSet rAnnotationsInDefaultGraph = queryExecution.execSelect();
			while (rAnnotationsInDefaultGraph.hasNext()) {
				QuerySolution sol = rAnnotationsInDefaultGraph.nextSolution();
				Resource annUri = sol.getResource("s");
				RDFNode proUri = sol.get("p");
				RDFNode objUri = sol.get("o");
				System.out.println(annUri.toString() + ' - ' + proUri.toString() + ' - ' + objUri.toString());
			}
		} catch (Exception e) {
			System.out.println('querying ex....' + e.getMessage());
			e.printStackTrace();
		} 
	}
}
