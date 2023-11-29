import sys

class TestExternalProgramAction:
    @staticmethod
    def main():
        args = sys.argv[1:]

        if args:
            print(args)
        else:
            print("You didn't insert command line arguments")

if __name__ == "__main__":
    TestExternalProgramAction.main()
