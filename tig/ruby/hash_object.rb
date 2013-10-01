require 'digest/sha1'

def sha1(input)
  git_sha1("blob", input)
end

def git_sha1(type, input)
  Digest::SHA1.hexdigest("#{type} #{input.length}\0#{input}")
end

# ARGF handles filename input from command line or STDIN if no filename
#puts sha1(ARGF.read)
