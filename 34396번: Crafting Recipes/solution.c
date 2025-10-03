#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

#define BASE 1000000000u
typedef struct {
    uint32_t *d;
    int n;
    int cap;
} Big;

static void big_init(Big* a){ a->d=NULL; a->n=0; a->cap=0; }
static void big_reserve(Big* a, int need){
    if(a->cap >= need) return;
    int nc = a->cap ? a->cap : 1;
    while(nc < need) nc <<= 1;
    a->d = (uint32_t*)realloc(a->d, sizeof(uint32_t)*nc);
    a->cap = nc;
}
static void big_trim(Big* a){
    while(a->n > 1 && a->d[a->n-1]==0) a->n--;
    if(a->n==0){ big_reserve(a,1); a->d[0]=0; a->n=1; }
}
static void big_from_u64(Big* a, uint64_t x){
    big_reserve(a, 3);
    a->n = 0;
    if(x==0){ a->d[0]=0; a->n=1; return; }
    while(x){
        a->d[a->n++] = (uint32_t)(x % BASE);
        x /= BASE;
    }
}
static void big_set(Big* dst, const Big* src){
    big_reserve(dst, src->n);
    memcpy(dst->d, src->d, sizeof(uint32_t)*src->n);
    dst->n = src->n;
}
static void big_add(Big* a, const Big* b){
    int n = (a->n > b->n) ? a->n : b->n;
    big_reserve(a, n+1);
    uint64_t carry = 0;
    for(int i=0;i<n;i++){
        uint64_t ai = (i < a->n) ? a->d[i] : 0;
        uint64_t bi = (i < b->n) ? b->d[i] : 0;
        uint64_t s = ai + bi + carry;
        a->d[i] = (uint32_t)(s % BASE);
        carry = s / BASE;
    }
    a->n = n;
    if(carry){
        a->d[a->n++] = (uint32_t)carry;
    }
}
static void big_mul_small(Big* a, uint32_t m){
    if(m==0){ a->d[0]=0; a->n=1; return; }
    if(m==1){ return; }
    big_reserve(a, a->n+2);
    uint64_t carry = 0;
    for(int i=0;i<a->n;i++){
        uint64_t prod = (uint64_t)a->d[i]*m + carry;
        a->d[i] = (uint32_t)(prod % BASE);
        carry = prod / BASE;
    }
    while(carry){
        a->d[a->n++] = (uint32_t)(carry % BASE);
        carry /= BASE;
    }
}
static char* big_to_string(const Big* a){
    int approx = a->n*10 + 2;
    char* s = (char*)malloc(approx);
    int pos = 0;
    int i = a->n - 1;
    pos += sprintf(s+pos, "%u", a->d[i]);
    for(i=a->n-2;i>=0;i--){
        pos += sprintf(s+pos, "%09u", a->d[i]);
    }
    s[pos] = '\0';
    return s;
}
static void big_free(Big* a){
    free(a->d);
    a->d=NULL; a->n=0; a->cap=0;
}

typedef struct {
    char* name;
    int isRaw;
    int hasRecipe;
    int computed;
    int P;
    int* part;
    int* qty;
    Big cost;
} Node;

static char* xstrdup(const char* s){
    size_t L = strlen(s);
    char* r = (char*)malloc(L+1);
    memcpy(r,s,L+1);
    return r;
}

#define MAXNODES 450
static Node nodes[MAXNODES];
static int nodeCnt = 0;

static int find_id(const char* name){
    for(int i=0;i<nodeCnt;i++){
        if(strcmp(nodes[i].name, name)==0) return i;
    }
    return -1;
}
static int get_id(const char* name){
    int k = find_id(name);
    if(k!=-1) return k;
    int u = nodeCnt++;
    nodes[u].name = xstrdup(name);
    nodes[u].isRaw = 0;
    nodes[u].hasRecipe = 0;
    nodes[u].computed = 0;
    nodes[u].P = 0;
    nodes[u].part = NULL;
    nodes[u].qty = NULL;
    big_init(&nodes[u].cost);
    big_from_u64(&nodes[u].cost, 0);
    return u;
}

static void compute_cost_into(int u, Big* out){
    if(nodes[u].computed){
        big_set(out, &nodes[u].cost);
        return;
    }
    if(nodes[u].isRaw){
        big_set(out, &nodes[u].cost);
        return;
    }
    Big total; big_init(&total); big_from_u64(&total, 0);
    for(int i=0;i<nodes[u].P;i++){
        int v = nodes[u].part[i];
        int q = nodes[u].qty[i];
        Big sub; big_init(&sub);
        compute_cost_into(v, &sub);
        big_mul_small(&sub, (uint32_t)q);
        big_add(&total, &sub);
        big_free(&sub);
    }
    big_set(&nodes[u].cost, &total);
    nodes[u].computed = 1;
    big_set(out, &nodes[u].cost);
    big_free(&total);
}

int main(void){
    int N;
    if(scanf("%d", &N)!=1) return 0;

    for(int i=0;i<N;i++){
        char buf[256]; long long c;
        scanf("%255s %lld", buf, &c);
        int u = get_id(buf);
        nodes[u].isRaw = 1;
        big_from_u64(&nodes[u].cost, (uint64_t)c);
        nodes[u].computed = 1;
    }

    int M; scanf("%d", &M);
    for(int i=0;i<M;i++){
        char comp[256]; int P;
        scanf("%255s %d", comp, &P);
        int u = get_id(comp);
        nodes[u].hasRecipe = 1;
        nodes[u].P = P;
        nodes[u].part = (int*)malloc(sizeof(int)*P);
        nodes[u].qty  = (int*)malloc(sizeof(int)*P);
        for(int j=0;j<P;j++){
            char pn[256]; int q;
            scanf("%255s %d", pn, &q);
            int v = get_id(pn);
            nodes[u].part[j] = v;
            nodes[u].qty[j] = q;
        }
    }

    int cap = find_id("Capstone");
    Big ans; big_init(&ans);
    compute_cost_into(cap, &ans);
    char* s = big_to_string(&ans);
    puts(s);

    free(s);
    big_free(&ans);
    for(int i=0;i<nodeCnt;i++){
        free(nodes[i].name);
        free(nodes[i].part);
        free(nodes[i].qty);
        big_free(&nodes[i].cost);
    }
    return 0;
}