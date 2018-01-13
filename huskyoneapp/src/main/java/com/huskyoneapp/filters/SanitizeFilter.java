package com.huskyoneapp.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

@Component
public class SanitizeFilter implements Filter {
	
	private static final boolean debug = false;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
        
			chain.doFilter(wrappedRequest, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	class RequestWrapper extends HttpServletRequestWrapper {
        
		public RequestWrapper(HttpServletRequest request) {
	            super(request);
	        }
	        
	        public String sanitize(String string){
	            string = Jsoup.clean(string, Whitelist.none());
	       
	            return string;
	        }
	        
	        protected Hashtable localParams = null;
	        
	        public void setParameter(String name, String[] values) {
	            if (debug) {
	                System.out.println("NewFilter::setParameter(" + name + "=" + values + ")" + " localParams = " + localParams);
	            }
	            
	            if (localParams == null) {
	                localParams = new Hashtable();
	                // Copy the parameters from the underlying request.
	                Map wrappedParams = getRequest().getParameterMap();
	                Set keySet = wrappedParams.keySet();
	                for (Iterator it = keySet.iterator(); it.hasNext();) {
	                    Object key = it.next();
	                    Object value = wrappedParams.get(key);
	                    localParams.put(key, value);
	                }
	            }
	            localParams.put(name, values);
	        }
	        
	        @Override
	        public String getParameter(String name) {
	            if (debug) {
	                System.out.println("NewFilter::getParameter(" + name + ") localParams = " + localParams);
	            }
	            if (localParams == null) {
	            	String value = getRequest().getParameter(name);
	            	if(value!=null)
	            		return sanitize(value);
	            	else
	            		return value;
	            }
	            Object val = localParams.get(name);
	            if (val instanceof String) {
	                return (String) val;
	            }
	            if (val instanceof String[]) {
	                String[] values = (String[]) val;
	                return values[0];
	            }
	            return (val == null ? null : val.toString());
	        }
	        
	        @Override
	        public String[] getParameterValues(String name) {
	            if (debug) {
	                System.out.println("NewFilter::getParameterValues(" + name + ") localParams = " + localParams);
	            }
	            if (localParams == null) {
	                return getRequest().getParameterValues(name);
	            }
	            return (String[]) localParams.get(name);
	        }
	        
	        @Override
	        public Enumeration getParameterNames() {
	            if (debug) {
	                System.out.println("NewFilter::getParameterNames() localParams = " + localParams);
	            }
	            if (localParams == null) {
	                return getRequest().getParameterNames();
	            }
	            return localParams.keys();
	        }        
	        
	        @Override
	        public Map getParameterMap() {
	            if (debug) {
	                System.out.println("NewFilter::getParameterMap() localParams = " + localParams);
	            }
	            if (localParams == null) {
	                return getRequest().getParameterMap();
	            }
	            return localParams;
	        }
	    }
	
}
