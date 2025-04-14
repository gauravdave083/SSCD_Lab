%{
#include "y.tab.h"
%}

%%

[0-9]+                { yylval.str = strdup(yytext); return NUM; }
[a-zA-Z_][a-zA-Z0-9_]* { yylval.str = strdup(yytext); return ID; }

"+"                   { return PLUS; }
"-"                   { return MINUS; }
"*"                   { return MUL; }
"/"                   { return DIV; }

"("                   { return LPAREN; }
")"                   { return RPAREN; }

[ \t\n]+              ; // Ignore whitespace
.                     { return yytext[0]; }

%%

int yywrap() {
    return 1;
}
