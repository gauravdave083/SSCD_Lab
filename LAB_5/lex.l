%{
#include <stdio.h>
%}

%%
"int"       { printf("TOKEN: KEYWORD (int)\n"); }
"float"     { printf("TOKEN: KEYWORD (float)\n"); }
"if"        { printf("TOKEN: KEYWORD (if)\n"); }
"else"      { printf("TOKEN: KEYWORD (else)\n"); }
"while"     { printf("TOKEN: KEYWORD (while)\n"); }
"return"    { printf("TOKEN: KEYWORD (return)\n"); }

[0-9]+(\.[0-9]+)?   { printf("TOKEN: NUMBER (%s)\n", yytext); }
[a-zA-Z_][a-zA-Z0-9_]* { printf("TOKEN: IDENTIFIER (%s)\n", yytext); }

"=="        { printf("TOKEN: OPERATOR (==)\n"); }
"="         { printf("TOKEN: OPERATOR (=)\n"); }
"!="        { printf("TOKEN: OPERATOR (!=)\n"); }
"<="        { printf("TOKEN: OPERATOR (<=)\n"); }
">="        { printf("TOKEN: OPERATOR (>=)\n"); }
"<"         { printf("TOKEN: OPERATOR (<)\n"); }
">"         { printf("TOKEN: OPERATOR (>)\n"); }
"+"         { printf("TOKEN: OPERATOR (+)\n"); }
"-"         { printf("TOKEN: OPERATOR (-)\n"); }
"*"         { printf("TOKEN: OPERATOR (*)\n"); }
"/"         { printf("TOKEN: OPERATOR (/)\n"); }

";"         { printf("TOKEN: SEMICOLON\n"); }
","         { printf("TOKEN: COMMA\n"); }
"{"         { printf("TOKEN: LEFT_BRACE\n"); }
"}"         { printf("TOKEN: RIGHT_BRACE\n"); }
"("         { printf("TOKEN: LEFT_PAREN\n"); }
")"         { printf("TOKEN: RIGHT_PAREN\n"); }

"//".*      { /* skip single-line comment */ }
[ \t\n]+    { /* skip whitespace */ }

.           { printf("UNKNOWN TOKEN: %s\n", yytext); }

%%

int main() {
    printf("Enter a C statement:\n");
    yylex();
    return 0;
}

int yywrap() {
    return 1;
}
