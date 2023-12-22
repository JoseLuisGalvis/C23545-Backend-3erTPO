package com.oradores.filters;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/*" })
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		List<String> origenesPermitidos = List.of("http://localhost:5500", "http://127.0.0.1:5500",
				"http://localhost:8080", "http://127.0.0.1:8080");

		String origin = ((HttpServletRequest) request).getHeader("origin");

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (origenesPermitidos.contains(origin)) {
			// ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin",
			// origin);
			httpResponse.setHeader("Access-Control-Allow-Origin", origin);
			httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			httpResponse.setHeader("Access-Control-Max-Age", "3600");
			httpResponse.setHeader("Access-Control-Allow-Headers",
					"Content-Type, Authorization, Accept, X-Requested-With, remember-me");
		}
		chain.doFilter(request, response);
	}

}
