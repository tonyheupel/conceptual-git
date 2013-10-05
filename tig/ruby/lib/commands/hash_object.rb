require 'digest/sha1'

class HashObject

  class << self

    def hash(input, type='blob')
      git_sha1(type, input)
    end


    def git_sha1(type, input)
      sha1("#{type} #{input.length}\0#{input}")
    end


    def sha1(input)
      Digest::SHA1.hexdigest(input)
    end

    # ARGF handles filename input from command line or STDIN if no filename
    #puts sha1(ARGF.read)

  end
end
