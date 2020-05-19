package com.sliit.cde.scannermodule.acc.service.java;

import com.sliit.cde.scannermodule.acc.service.SizeStringPatternService;

public class JavaLanguageSizeStringPatternService extends SizeStringPatternService {
    @Override
    protected String getOrSeparatedKeywords() {
        return "abstract|continue|new|assert|default|package|synchronized|" +
                "private|this|break|implements|protected|throw|import|public|throws|" +
                "enum|instanceof|return|transient|catch|extends|try|final|interface|" +
                "static|void|class|finally|strictfp|volatile|native|super";
    }

    @Override
    protected String getOrSeparatedIdentifierPrefixes() {
        return "class|for|case";
    }
}
