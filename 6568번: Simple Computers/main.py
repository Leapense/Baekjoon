import sys

def run_program(mem):
    pc = 0
    accu = 0
    while True:
        instr = mem[pc]
        pc = (pc + 1) % 32
        opcode = instr >> 5
        addr = instr & 0b11111
        if opcode == 0:
            mem[addr] = accu
        elif opcode == 1:
            accu = mem[addr]
        elif opcode == 2:
            if accu == 0:
                pc = addr
        elif opcode == 3:
            pass
        elif opcode == 4:
            accu = (accu - 1) % 256
        elif opcode == 5:
            accu = (accu + 1) % 256
        elif opcode == 6:
            pc = addr
        else:
            break
    return accu
    
def main():
    tokens = sys.stdin.buffer.read().split()
    out_lines = []
    
    for i in range(0, len(tokens), 32):
        chunks = tokens[i:i+32]
        if len(chunks) < 32:
            break
            
        mem = [int(b, 2) for b in chunks]
        accu_final = run_program(mem)
        out_lines.append(f"{accu_final:08b}")
        
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()