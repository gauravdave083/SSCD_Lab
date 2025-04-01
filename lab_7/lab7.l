%{
#include "lab7.tab.h"
%}

%%

"if"        { return IF; }
"do"        { return DO; }
"for"       { return FOR; }
"("         { return OP; }
")"         { return CP; }
"{"         { return OCB; }
"}"         { return CCB; }
"=="|"!="|"<"|">" { return CMP; }
";"         { return SC; }
"="         { return ASG; }
"+"|"-"|"*"|"/" { return OPR; }
","         { return COMMA; }
[0-9]+      { return NUM; }
[a-zA-Z][a-zA-Z0-9]* { return ID; }
[ \t\n]+    { /* Ignore whitespace */ }
.           { printf("Unexpected character: %s\n", yytext); }

%%

int yywrap() {
    return 1;
}
